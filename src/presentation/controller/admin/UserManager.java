package presentation.controller.admin;

import bussiness.classes.UserBussiness;
import entity.User;
import presentation.run.Main;
import presentation.util.IOFile;
import presentation.util.InputMethods;

public class UserManager {
    private static final UserBussiness userBussiness = new UserBussiness();

    public static void userManagementMenu() {
        while (true) {
            System.out.println("╔════════════════════════════════════╗");
            System.out.println("║              Manage User           ║");
            System.out.println("╠════════════════════════════════════╣");
            System.out.println("║  1. Hiển thị danh sách người dùng  ║");
            System.out.println("║  2. Tìm user bằng id               ║");
            System.out.println("║  3. Tìm user bằng tên              ║");
            System.out.println("║  4. Xóa user                       ║");
            System.out.println("║  5. Khóa tài khoản                 ║");
            System.out.println("║  6. Mở khóa tài khoản              ║");
            System.out.println("║  7. Thoát                          ║");
            System.out.println("╚════════════════════════════════════╝");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    displayAllUser();
                    break;
                case 2:
                    findUserById();
                    break;
                case 3:
                    findUserByName();
                    break;
                case 4:
                    deleteUserById();
                    break;
                case 5:
                    blockUser();
                    break;
                case 6:
                    unblockUser();
                    break;
                case 7:
                    Main.menuAdmin();
                    break;
                default:
                    System.err.println("Không đúng lựa chọn");
            }
        }
    }

private static void displayAllUser() {
    System.out.println(UserBussiness.users);
}

private static void findUserById() {
    System.out.print("Nhập ID người dùng: ");
    int id = InputMethods.getInteger();
    User user = userBussiness.findById(id);
    if (user != null) {
        System.out.println(user);
    } else {
        System.out.println("Không tìm thấy người dùng với ID này.");
    }
}

private static void findUserByName() {
    System.out.print("Nhập tên người dùng: ");
    String name = InputMethods.getString();
    User user = UserBussiness.findByName(name);
    if (user != null) {
        System.out.println(user);
    } else {
        System.out.println("Không tìm thấy người dùng với tên này.");
    }
}

private static void deleteUserById() {
    System.out.print("Nhập ID người dùng để xóa: ");
    int id = InputMethods.getInteger();
    boolean isDeleted = userBussiness.deleteById(id);
    if (isDeleted) {
        System.out.println("Người dùng đã được xóa.");
    } else {
        System.out.println("Không tìm thấy người dùng với ID này.");
    }
}

private static void blockUser() {
    System.out.print("Nhập ID người dùng để khóa: ");
    int id = InputMethods.getInteger();
    boolean isBlocked = userBussiness.blockUser(id);
    if (isBlocked) {
        System.out.println("Người dùng đã bị khóa.");
    } else {
        System.out.println("Không tìm thấy người dùng với ID này.");
    }
    IOFile.writeToFile(IOFile.USER_PATH, UserBussiness.users);
    IOFile.writeToFile(IOFile.USERLOGIN_PATH, UserBussiness.users);

}

private static void unblockUser() {
    System.out.print("Nhập ID người dùng để mở khóa: ");
    int id = InputMethods.getInteger();
    boolean isUnblocked = userBussiness.unblockUser(id);
    if (isUnblocked) {
        System.out.println("Người dùng đã được mở khóa.");
    } else {
        System.out.println("Không tìm thấy người dùng với ID này.");
    }
    IOFile.writeToFile(IOFile.USER_PATH, UserBussiness.users);
    IOFile.writeToFile(IOFile.USERLOGIN_PATH, UserBussiness.users);
}
}
