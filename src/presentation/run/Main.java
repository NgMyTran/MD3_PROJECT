package presentation.run;

import bussiness.classes.AuthBussiness;
//import bussiness.classes.CartBussiness;
import bussiness.classes.ProductBussiness;
import bussiness.classes.UserBussiness;
import bussiness.exception.UsernameAndPasswordException;
import entity.*;
import org.mindrot.jbcrypt.BCrypt;
import presentation.controller.admin.CategoryManager;
import presentation.controller.admin.ProductManager;
import presentation.controller.admin.UserManager;
import presentation.util.IOFile;
import presentation.util.InputMethods;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import static bussiness.classes.ProductBussiness.products;
import static bussiness.classes.UserBussiness.users;

public class Main {
    private static final AuthBussiness iAuthBussiness = new AuthBussiness();
    private static UserBussiness userBussiness = new UserBussiness();
    private static ProductBussiness productBussiness = new ProductBussiness();

    //--------------  MAIN -----------
    public static void main(String[] args) {
        Authentication(); // Call the Authentication method from the main method
    }

    public static void Authentication() {

        while (true) {
            System.out.println("╔════════════════════════════════════╗");
            System.out.println("║           Welcome to Store         ║");
            System.out.println("╠════════════════════════════════════╣");
            System.out.println("║  1. Đăng nhập                      ║");
            System.out.println("║  2. Đăng kí                        ║");
            System.out.println("║  3. Thoát                          ║");
            System.out.println("╚════════════════════════════════════╝");
            byte choice = InputMethods.getByte(); // Assuming InputMethods.getByte() is a valid method for user input
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    System.out.println("Tạm biệt");
                    return; // Exit the method (and thus the loop) when choice is 3
                default:
                    System.err.println("Ko đúng lựa chọn");
            }
        }
    }

    //--------------  ĐĂNG KÝ-----------
    private static void register() {
        System.out.println("------------------Register----------------");
        User user = new User();
        while (true) {
            System.out.println("Nhập tên đăng nhập: ");
            String name = InputMethods.getString();
            System.out.println("ten dang nhap rong? " + name.isEmpty());
                user.setUserName(name);
            //Nhập và kiểm tra email
            while (true) {
                System.out.println("Nhập email: ");
                String email = InputMethods.getString();
                if (email.matches("^[a-zA-Z0-9_]+@[a-zA-Z]+\\.[a-zA-Z]+$")) {
                    user.setEmail(email);
                    break;
                } else {
                    System.out.println("Email không hợp lệ. Vui lòng nhập lại.");
                }
            }
            System.out.println("da toi day 2");
            //Nhập và kiểm tra mật khẩu
            while (true) {
                System.out.println("Nhập mật khẩu: ");
                String password = InputMethods.getString();
                if (password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")) {
                    user.setPassword(password);
                    break;
                } else {
                    System.out.println("Mật khẩu phải có 1 chữ viết hoa, 1 chữ viết thường, 1 số và có 8 kí tự trở lên. Vui lòng nhập lại.");
                }
            }
            System.out.println("da toi day 3");
            // Confirm pass
            while (true) {
                System.out.println("Confirm password: ");
                String confirmPassword = InputMethods.getString();
                if (confirmPassword.equals(user.getPassword())) {
                    //Nhập và kiểm tra số điện thoại
                    while (true) {
                        System.out.println("Nhập số điện thoại: ");
                        String phone = InputMethods.getString();
                        if (phone.matches("^(\\+84|0)\\d{8,9}$")) {
                            user.setPhone(phone);
                            break;
                        } else {
                            System.out.println("Số điện thoại không hợp lệ. Vui lòng nhập lại.");
                        }
                    }
                    //Nhập và kiểm tra address
                    while (true) {
                        System.out.println("Nhập địa chỉ: ");
                        String address = InputMethods.getString();
                        if (!address.isEmpty()) {
                            user.setAddress(address);
                            break;
                        } else {
                            System.out.println("Địa chỉ không hợp lệ. Vui lòng nhập lại.");
                        }
                    }
                    //Nhập và kiểm tra birthday
                    while (true) {
                        System.out.println("Nhập ngày tháng năm sinh (dd/MM/yyyy): ");
                        String birthdayInput = InputMethods.getString();
                        if (!birthdayInput.isEmpty()) {
                            try {
                                LocalDate birthday = LocalDate.parse(birthdayInput, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                                user.setBirthday(birthday);
                                break;
                            } catch (DateTimeParseException e) {
                                System.out.println("Ngày tháng không hợp lệ. Vui lòng nhập lại.");
                            }
                        } else {
                            System.out.println("Ngày tháng không được để trống. Vui lòng nhập lại.");
                        }
                    }
                    break;
                } else {
                    System.out.println("Mật khẩu không khớp, vui lòng thử lại");
                }
            }
            iAuthBussiness.signUp(user);
            System.out.println("Đăng kí thành công");
            login();
        }
    }

    //--------------  ĐĂNG NHẬP------------
    private static void login() {
        System.out.println("=================Đăng nhập=================");
        System.out.println("Nhập email :");
        String userEmail = InputMethods.getString();
        System.out.println("Nhập password");
        String password = InputMethods.getString();
        try {
            User userLogin = iAuthBussiness.signIn(userEmail, password);
            IOFile.writeUserLogin(userLogin);
            // Xét quyền user
            if (userLogin.getRoleName().equals(RoleName.ADMIN)) {
                menuAdmin();
            } else if (userLogin.getRoleName().equals(RoleName.USER)) {
                menuUser();
            }
        } catch (UsernameAndPasswordException e) {
            System.out.println(e.getMessage()); // Hiển thị thông báo lỗi từ signIn
        }
    }

    //-------------- END MAIN -----------


    //--------------  MENU ADMIN-----------
    public static void menuAdmin() {
        System.out.println("╔═════════════════════════════════════════════╗");
        System.out.println("║ -----------Chào mừng ADMIN----------        ║");
        System.out.println("╠═════════════════════════════════════════════╣");
        System.out.println("║ 1. Quản lý danh mục                         ║");
        System.out.println("║ 2. Quản lý sản phẩm                         ║");
        System.out.println("║ 3. Quản lý người dùng                       ║");
        System.out.println("║ 4. Thoát                                    ║");
        System.out.println("╚═════════════════════════════════════════════╝");
        byte choice = InputMethods.getByte();
        switch (choice) {
            case 1:
                CategoryManager.categoryManagementMenu();
                break;
            case 2:
                ProductManager.productManagementMenu();
                break;
            case 3:
                UserManager.userManagementMenu();
                break;
            case 4:
                System.out.println("Đã thoát trang quản lý của ADMIN");
                Authentication();
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ");
                break;
        }
    }
    //--------------END MENU ADMIN -----------

    //--------------MENU USER -----------
    private static void menuUser() {
        String userName = IOFile.readUserName();
            System.out.println("╔══════════════════════════════════════════════════════════════════╗");
            System.out.println("  Chào mừng " + "( " + userName + " )" + " đến cửa hàng để mua sắm ");
            System.out.println("╠══════════════════════════════════════════════════════════════════╣");
            System.out.println("║  1. Xem tất cả mặt hàng                                          ║");
            System.out.println("║  2. Xem mặt hàng theo danh mục                                   ║");
            System.out.println("║  3. Thêm mặt hàng vào giỏ                                        ║");
            System.out.println("║  4. Xem giỏ hàng                                                 ║");
            System.out.println("║  5. Xóa mặt hàng khỏi giỏ                                        ║");
            System.out.println("║  6. Hiển thị thông tin cá nhân                                   ║");
            System.out.println("║  7. Sửa thông tin cá nhân                                        ║");
            System.out.println("║  8. Đổi mật khẩu đăng nhập                                       ║");
            System.out.println("║  9. Thanh toán                                                   ║");
            System.out.println("║ 10. Thoát                                                        ║");
            System.out.println("╚══════════════════════════════════════════════════════════════════╝");
            System.out.print("Nhập lựa chọn của bạn: ");
        byte choice = InputMethods.getByte();
        switch (choice) {
            case 1:
//                showAllProduct();
                return;
            case 2:
                productBussiness.displayProductsByCategory();
                break;
            case 3:
                //addProductToCart(user);
            case 4:
                //showCart(user);
                break;
            case 5:
//                removeProductFromCart();
                break;
            case 6:
                displayUserInfo();
                break;
            case 7:
                updateUserInfo();
                break;
            case 8:
                changePassword();
                break;
            case 9:
                // 8. Thanh toán
                break;
            case 10:
                Authentication();
                break;
//                return;
            default:
                System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập lại");
                break;
        }
    }

    private static void displayUserInfo() {
        User user = IOFile.readUserLogin();
        if (user != null) {
            userBussiness.displayInfo(user);
        } else {
            System.out.println("Không tìm thấy thông tin người dùng đăng nhập.");
        }
    }

private static void updateUserInfo() {
    User user = IOFile.readUserLogin();
    if (user != null) {
        System.out.println("Cập nhật thông tin cá nhân:");
        System.out.println("Nhập tên đăng nhập mới:");
        String userName = InputMethods.getString();
        if (!userName.isEmpty()) {
            user.setUserName(userName);
        }

        System.out.println("Nhập địa chỉ mới:");
        String address = InputMethods.getString();
        if (!address.isEmpty()) {
            user.setAddress(address);
        }

        System.out.println("Nhập số điện thoại mới:");
        String phone = InputMethods.getString();
        if (!phone.isEmpty()) {
            user.setPhone(phone);
        }

        boolean updated = iAuthBussiness.updateInfo(user);
        if (updated) {
            IOFile.writeUserLogin(user); // Cập nhật tệp đăng nhập người dùng với thông tin mới
            System.out.println("Cập nhật thông tin thành công.");
        } else {
            System.out.println("Cập nhật thông tin thất bại.");
        }
    } else {
        System.out.println("Không tìm thấy thông tin người dùng đăng nhập.");
    }
}

    private static void changePassword() {
        User user = IOFile.readUserLogin();
        System.out.print("Nhập mật khẩu cũ: ");
        String oldPass = InputMethods.getString();
        if (user != null && BCrypt.checkpw(oldPass, user.getPassword())) {
            System.out.print("Nhập mật khẩu mới: ");
            String newPassword = InputMethods.getString();
            System.out.print("Xác nhận mật khẩu mới: ");
            String confirmPassword = InputMethods.getString();
            if (!newPassword.isEmpty() && newPassword.equals(confirmPassword)) {
                String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
                user.setPassword(hashedPassword);
                boolean updated = iAuthBussiness.updatePassword(user);
                if (updated) {
                    System.out.println("Cập nhật mật khẩu thành công.");

                } else {
                    System.out.println("Cập nhật mật khẩu thất bại.");
                    menuUser();
                }
            } else {
                System.err.println("Mật khẩu xác nhận không khớp.");
                menuUser();
            }
        } else {
            System.err.println("Mật khẩu cũ không chính xác.");
            menuUser();
        }
    }
    //--------------END MENU USER -----------

}
