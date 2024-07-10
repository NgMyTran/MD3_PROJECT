package entity;

import java.io.Serializable;
import java.time.LocalDate;

public class User implements Serializable {
    private int userId;
    private String userName;
    private String email;
    private String password;
    private  String address;
    private String phone;
    private LocalDate birthday;
    private boolean isBlocked;
    private RoleName roleName;
    private static  int nextId  = 1;
//    private static final long serialVersionUID = 1L;

    public User(){
        this.userId = ++nextId;
    };
    public User(int userId, String userName, String email, String password, String address, String phone , LocalDate birthday, boolean isBlocked, RoleName roleName) {
        this.userId =userId ;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.birthday = birthday;
        this.isBlocked = false;
        this.roleName = roleName;
    }

    public int getUserId() {
        return userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

//    public String toString() {
//        return String.format(
//                """
//                            User{
//                            id=%d,
//                            name='%s',
//                            email='%s',
//                            address='%s',
//                            phone='%s',
//                            cart=%s
//                        }
//                        """, userId, userName, email, address, phone,
////                cart
//        );
//    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                "}\n";
    }
}
