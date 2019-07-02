package hibernate.lesson4.service;

import hibernate.lesson4.DAO.UserDAO;
import hibernate.lesson4.model.User;

public class UserService {

    UserDAO userDAO = new UserDAO();


    public void registerUser(User user) throws Exception{

        userDAO.registerUser(user);

    }





}
