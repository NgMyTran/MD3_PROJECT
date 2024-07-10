package bussiness.classes;

import bussiness.exception.UsernameAndPasswordException;
import bussiness.interfaces.IAuthDesign;
import entity.RoleName;
import entity.User;
import org.mindrot.jbcrypt.BCrypt;
import presentation.util.IOFile;

import java.io.Serializable;
import java.util.List;

public class AuthBussiness implements IAuthDesign, Serializable {
    private static List<User> users;
    static {
        users = IOFile.readFromFile(IOFile.USER_PATH);
    }

    @Override
    public User signIn(String userEmail, String password) {
//        System.out.println("da vao nekkkkkkk");
        User userLogin = users.stream()
                .filter(u -> u.getEmail().equals(userEmail) && BCrypt.checkpw(password, u.getPassword()))
                .findFirst()
                .orElseThrow(() -> new UsernameAndPasswordException("username or password incorrect!"));
        return userLogin;
    }

    @Override
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
}
