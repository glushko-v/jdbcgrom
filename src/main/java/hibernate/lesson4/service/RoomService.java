package hibernate.lesson4.service;

import hibernate.lesson4.DAO.DAO;
import hibernate.lesson4.DAO.RoomDAO;
import hibernate.lesson4.DAO.UserDAO;
import hibernate.lesson4.model.Room;
import hibernate.lesson4.model.User;
import org.hibernate.SessionFactory;

import java.util.Date;

public class RoomService extends DAO<Room> {
    //1. проверка даты доступности
    //2. проверка isBooked

    RoomDAO roomDAO = new RoomDAO();
    UserDAO userDAO = new UserDAO();


    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo) {

        //1. зарегистрирован ли юзер++
        //2. залогинен ли юзер++




    }

    //TODO


    @Override
    public SessionFactory createSessionFactory() {
        return super.createSessionFactory();
    }

    @Override
    public Room save(Room room) {
        return super.save(room);
    }

    @Override
    public void delete(long id) {

        roomDAO.delete(id);

    }

    @Override
    public Room findById(long id) {
        return null;
    }

    @Override
    public Room update(Room room, long id) {
        return null;
    }
}
