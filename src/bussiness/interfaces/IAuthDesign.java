package bussiness.interfaces;

import entity.User;

public interface IAuthDesign {
    User signIn(String userEmail, String password);
    void signUp(User user);
}
