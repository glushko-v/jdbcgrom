package hibernate.lesson4.service;

import hibernate.lesson4.DAO.RoomDAO;
import hibernate.lesson4.DAO.UserDAO;
import hibernate.lesson4.model.User;

import java.util.Date;

public class RoomService {
    //1. проверка даты доступности
    //2. проверка isBooked

    RoomDAO roomDAO = new RoomDAO();
    UserDAO userDAO = new UserDAO();


    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo) {

        //1. зарегистрирован ли юзер++
        //2. залогинен ли юзер++


        if (userDAO.findById(userId).isRegistered() && userDAO.findById(userId).isLoggedIn())
            roomDAO.bookRoom(roomId, userId, dateFrom, dateTo);


    }
}
