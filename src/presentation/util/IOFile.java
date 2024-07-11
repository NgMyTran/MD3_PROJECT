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

    public static <T> List<T> readFromFile(String path) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(path);
            ois = new ObjectInputStream(fis);
            return (List<T>) ois.readObject();
//
        } catch (FileNotFoundException e) {
            System.err.println("File doesn't exist");
        } catch (EOFException e) {
            System.err.println("file trống");
            User admin = new User(0, "admin", "admin@gmail.com", BCrypt.hashpw("admin123", BCrypt.gensalt()), null, null, null, false, RoleName.ADMIN);
            List<User> users = new ArrayList<>();
            users.add(admin);
            writeToFile(USER_PATH, users);
            return (List<T>) users;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }
    // Ghi danh sách vào tệp

    public static <T> void writeToFile(String path, List<T> data) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(path);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(data);
            System.out.println("Object has been written to " + path);
        } catch (IOException e) {
            System.err.println("eee");
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
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


    // Ghi danh sách người dùng vào tệp
    public static void writeUsersToFile(List<User> data) {
        writeToFile(USER_PATH, data);
    }

}
