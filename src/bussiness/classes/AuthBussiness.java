package bussiness.classes;

import bussiness.exception.UsernameAndPasswordException;
import entity.RoleName;
import entity.User;
import org.mindrot.jbcrypt.BCrypt;
import presentation.util.IOFile;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class AuthBussiness implements Serializable {
    private static List<User> users;
    static {
        users = IOFile.readFromFile(IOFile.USER_PATH);
    }

    public void signUp(User user) {
        user.setBlocked(false);
        user.setRoleName(RoleName.USER);
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        users.add(user);
        IOFile.writeToFile(IOFile.USER_PATH, users);
    }

public User signIn(String userEmail, String password) throws UsernameAndPasswordException {
//    System.out.println("da vao nekk" + Arrays.toString(users.toArray()));
    User user = users.stream()
            .filter(u -> u.getEmail().equals(userEmail))
            .findFirst()
            .filter(u -> BCrypt.checkpw(password, u.getPassword())) // Kiểm tra mật khẩu
            .orElseThrow(() -> new UsernameAndPasswordException("Tên đăng nhập hoặc mật khẩu không đúng!"));
    if (user.isBlocked()) {
        throw new UsernameAndPasswordException("Tài khoản của bạn đã bị khóa. Vui lòng liên hệ với quản trị viên.");
    }
    return user;
}
    public boolean updateInfo(User updatedUser) {
        for (User user : users) {
            if (user.getUserId() == updatedUser.getUserId()) {
                System.out.println("user cũ" + user);
                user.setUserName(updatedUser.getUserName());
                user.setEmail(updatedUser.getEmail());
                user.setAddress(updatedUser.getAddress());
                user.setPhone(updatedUser.getPhone());
                IOFile.writeToFile(IOFile.USER_PATH, users);
                return true;
            }
        }
        return false; // User with given ID not found
    }


    public boolean updatePassword(User updatedUser) {
        for (User user : users) {
            if (user.getUserId() == updatedUser.getUserId()) {
                System.out.println("user cũ" + user);
                user.setPassword(updatedUser.getPassword());
                IOFile.writeToFile(IOFile.USER_PATH, users);
                // Optionally update password
            }
        }
        return true;
    }
}
