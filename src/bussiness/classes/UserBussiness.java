package bussiness.classes;

import bussiness.interfaces.IUserDesign;
import entity.User;
import presentation.util.IOFile;

import java.util.Arrays;
import java.util.List;

public class UserBussiness implements IUserDesign {
    public static List<User> users;

    public UserBussiness() {
        users= IOFile.readFromFile(IOFile.USER_PATH);
//        System.out.println("du lieu trong file   ");
        System.out.println();
    };

    //Admin
    @Override
    public boolean create(User user) {
        users.add(user);
        IOFile.writeToFile(IOFile.USER_PATH, users);
        return true;
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public boolean update(User user) {
        users.set(users.indexOf(findById(user.getUserId())), user);
        return true;
    }

    //Admin
    @Override
    public boolean deleteById(Integer id) {
        users.remove(this.findById(id));
        IOFile.writeToFile(IOFile.USER_PATH, users);
        return false;
    }

    @Override
    public User findById(Integer id) {
        for (User user : users) {
            if(user.getUserId()==id)
                return user;
        }
        return null;
    }


    public static User findByName(String name) {
        for (User user : users) {
            if (user.getUserName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null;
    }

    public void displayInfo(User user) {
        if (user != null) {
            System.out.println(user);
        } else {
            System.out.println("Không tìm thấy thông tin cá nhân. Vui lòng kiểm tra lại email và mật khẩu.");
        }
    }


//    public void logout() {
//        users.clear();
//        IOFile.writeToFile(IOFile.USER_PATH, users); // Save empty user list to file
//        System.out.println("Đăng xuất thành công.");
//    }
}
