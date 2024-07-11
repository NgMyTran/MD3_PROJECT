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

    public User signIn(String userEmail, String password) {
       System.out.println("da vao nekkkkkkk" + Arrays.toString(users.toArray()));
        User userLogin = users.stream()
                .filter(u -> u.getEmail().equals(userEmail) && BCrypt.checkpw(password, u.getPassword()))
                .findFirst()
                .orElseThrow(() -> new UsernameAndPasswordException("username or password incorrect!"));
        return userLogin;
    }

    public void signUp(User user) {
        user.setBlocked(false);
        user.setRoleName(RoleName.USER);
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
//        user.setUserId(IOFile.getAutoId()); // Lấy autoId từ IOFile để gán cho người dùng mới
//        IOFile.setAutoId(user.getUserId() + 1); // Cập nhật autoId cho người dùng tiếp theo
//        IOFile.createUser(user);
        users.add(user);
        IOFile.writeToFile(IOFile.USER_PATH, users);
    }

    public boolean updateInfo(User updatedUser) {
        for (User user : users) {
            if (user.getUserId() == updatedUser.getUserId()) {
                System.out.println("user cu" + user);
                user.setUserName(updatedUser.getUserName());
                user.setEmail(updatedUser.getEmail());
                user.setAddress(updatedUser.getAddress());
                user.setPhone(updatedUser.getPhone());
                user.setPassword(updatedUser.getPassword()); // Optionally update password
                IOFile.writeToFile(IOFile.USER_PATH, users);
                return true;
            }
        }
        return false; // User with given ID not found
    }
}
