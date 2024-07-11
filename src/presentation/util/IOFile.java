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
        try {
            File file = new File(path);
            // Ensure the parent directory exists and create the file if it does not exist
            if (!file.exists()) {
                if (file.getParentFile() != null) file.getParentFile().mkdirs();
                file.createNewFile();
                // If the file is the user file, initialize it with a default admin user
                if (path.equals(USER_PATH)) {
                    List<User> users = new ArrayList<>();
                    User adminUser = new User(0, "admin", "admin@gmail.com", BCrypt.hashpw("admin123", BCrypt.gensalt()), null, null, null, false, RoleName.ADMIN);
                    users.add(adminUser);
                    writeToFile(USER_PATH, users);
                }
            }
            // Proceed to read from the file
            try (FileInputStream fis = new FileInputStream(path);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                return (List<T>) ois.readObject();
            } catch (EOFException e) {
                System.err.println("File is empty.");
                return new ArrayList<>(); // Return an empty list instead of null
            }
        } catch (FileNotFoundException e) {
            System.err.println("Unexpected error: File should have been created.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(); // Return an empty list instead of null to handle errors better
    }

    // Writes objects to a file
    public static <T> void writeToFile(String path, List<T> data) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(data);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Reads the logged-in user from a file
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

    // Writes the logged-in user to a file
    public static void writeUserLogin(User userLogin) {
        try (FileOutputStream fos = new FileOutputStream(USERLOGIN_PATH);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(userLogin);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Reads the username of the logged-in user from a file
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
