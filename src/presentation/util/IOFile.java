package presentation.util;

import entity.RoleName;
import entity.User;
import org.mindrot.jbcrypt.BCrypt;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IOFile {
    public static final String CATEGORY_PATH = "src/data/category.txt";
    public static final String PRODUCT_PATH = "src/data/product.txt";
    public static final String USER_PATH = "src/data/users.txt";
    public static final String USERLOGIN_PATH = "src/data/usersLogin.txt";
    public static final String CART_PATH = "src/data/cart.txt";

    private static int autoId = 1;

    // Đọc danh sách từ tệp
    public static <T> List<T> readFromFile(String path) {
        List<T> list = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(path);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            list = (List<T>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("File doesn't exist");
        } catch (EOFException e) {
            System.err.println("File is empty");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Ghi danh sách vào tệp
    public static <T> void writeToFile(String path, List<T> data) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(data);
            System.out.println("Data has been written to " + path);
        } catch (IOException e) {
            System.err.println("Error writing data to file");
            e.printStackTrace();
        }
    }

    // Đọc người dùng đã đăng nhập từ tệp
    public static User readUserLogin() {
        try (FileInputStream fis = new FileInputStream(USERLOGIN_PATH);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (User) ois.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("File doesn't have value");
        } catch (EOFException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    // Ghi người dùng đã đăng nhập vào tệp
    public static void writeUserLogin(User userLogin) {
        try (FileOutputStream fos = new FileOutputStream(USERLOGIN_PATH);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(userLogin);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Đọc tên người dùng từ tệp
    public static String readUserName() {
        try (FileInputStream fis = new FileInputStream(USERLOGIN_PATH);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            User userLogin = (User) ois.readObject();
            return userLogin.getUserName();
        } catch (FileNotFoundException e) {
            System.err.println("File doesn't have value");
        } catch (EOFException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    // Đọc danh sách người dùng từ tệp và cập nhật autoId
    public static List<User> readUsersFromFile() {
        List<User> users = readFromFile(USER_PATH);
        if (!users.isEmpty()) {
            autoId = users.get(users.size() - 1).getUserId() + 1;
            users.forEach(System.out::println);
        } else {
            autoId = 1;
        }
        return users;
    }

    // Ghi danh sách người dùng vào tệp
    public static void writeUsersToFile(List<User> data) {
        writeToFile(USER_PATH, data);
    }

//    // Tạo người dùng mới với autoId tự động tăng
//    public static User createUser(String userName, String email, String password, String address, String phone, LocalDate birthday, boolean isBlocked, RoleName roleName) {
//        User user = new User(autoId++, userName, email, password, address, phone, birthday, isBlocked, roleName);
//        return user;
//    }

//    // Lấy autoId hiện tại
//    public static int getAutoId() {
//        return autoId;
//    }

    // Cập nhật autoId
//    public static void setAutoId(int autoId) {
//        IOFile.autoId = autoId;
//    }
}
