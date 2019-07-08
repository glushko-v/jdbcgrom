package hibernate.lesson4.service;

import hibernate.lesson4.DAO.DAO;
import hibernate.lesson4.DAO.RoomDAO;
import hibernate.lesson4.DAO.UserDAO;
import hibernate.lesson4.model.Room;
import hibernate.lesson4.model.RoomSession;
import hibernate.lesson4.model.UserSession;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;

public class RoomService {
    //1. проверка даты доступности
    //2. проверка isBooked

    RoomDAO roomDAO = new RoomDAO();
    UserDAO userDAO = new UserDAO();


    SessionFactory sessionFactory;


    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo) {


        if (UserSession.isLoggedIn(userDAO.findById(userId)) && UserSession.isRegistered(userDAO.findById(userId))) {

            if (isRoomExists(findById(roomId))) roomDAO.bookRoom(roomId, userId, dateFrom, dateTo);
        }


    }


    public SessionFactory createSessionFactory() {

        if (sessionFactory == null) {

            sessionFactory = new Configuration().configure().buildSessionFactory();

        }

        return sessionFactory;

    }


    public Room save(Room room) throws Exception {
        if (!isRoomExists(room)) return roomDAO.save(room);
        else throw new Exception("Room " + room.getId() + " already exists");
    }


    public void delete(long id) {

        roomDAO.delete(id);

    }

    public Room findById(long id) {
        return roomDAO.findById(id);
    }


    public Room update(Room room, long id) throws Exception {
        if (isRoomExists(room)) return roomDAO.update(room, id);
        else throw new Exception("Room " + room.getId() + " does not exist");
    }

    private boolean isRoomExists(Room room) {

        Transaction tr = null;
        List<Room> rooms;

        try (Session session = createSessionFactory().openSession()) {


            tr = session.getTransaction();

            tr.begin();

            Query query = session.createSQLQuery("SELECT * FROM ROOM").addEntity(Room.class);
            rooms = query.getResultList();

            for (Room roomFromDB : rooms) {
                if (room.equals(roomFromDB)) return true;

            }

            tr.commit();


        } catch (HibernateException e) {
            System.err.println("ERROR");
            e.printStackTrace();
            if (tr != null) tr.rollback();
        }

        return false;

    }

    public void cancelReservation(long roomId, long userId){

        if (UserSession.isLoggedIn(userDAO.findById(userId)) && UserSession.isRegistered(userDAO.findById(userId))){

            roomDAO.cancelReservation(roomId, userId);

        }

    }
}
