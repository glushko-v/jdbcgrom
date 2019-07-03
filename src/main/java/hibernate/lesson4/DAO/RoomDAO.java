package hibernate.lesson4.DAO;

import hibernate.lesson4.model.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.Date;
import java.util.List;


public class RoomDAO extends DAO<Room> {

    @Override
    SessionFactory createSessionFactory() {
        return super.createSessionFactory();
    }

    @Override
    public Room save(Room room) {
        return super.save(room);
    }

    @Override
    public void delete(long id) {

        Session session = null;
        Transaction tr = null;

        try {

            session = createSessionFactory().openSession();
            tr = session.getTransaction();

            tr.begin();

//            Query query = session.createSQLQuery("DELETE FROM ROOM WHERE ID_ROOM =?").addEntity(Room.class);
//            query.setParameter(1, id);
//            query.executeUpdate();

            Room room = session.get(Room.class, id);
            session.delete(room);


            tr.commit();

            System.out.println("Deleted");


        } catch (HibernateException e) {
            System.err.println("ERROR");
            System.err.println(e.getMessage());
            if (tr != null) tr.rollback();
        } finally {
            if (session != null) session.close();
        }

    }

    @Override
    public Room findById(long id) {

        Session session = null;
        Transaction tr = null;

        try {

            session = createSessionFactory().openSession();
            tr = session.getTransaction();

            tr.begin();


            Room room = session.get(Room.class, id);


            tr.commit();

            System.out.println(room);


            return room;


        } catch (HibernateException e) {
            System.err.println("ERROR");
            System.err.println(e.getMessage());
            if (tr != null) tr.rollback();
        } finally {
            if (session != null) session.close();
        }


        return null;
    }

    @Override
    public Room update(Room room, long id) {
        Session session = null;
        Transaction tr = null;

        try {

            session = createSessionFactory().openSession();
            tr = session.getTransaction();

            tr.begin();

            Query query = session.createSQLQuery("UPDATE ROOM SET NUMBER_OF_GUESTS = ?, PRICE = ? BREAKFAST_INCLUDED = ?, PETS_ALLOWED = ?, " +
                    "DATE_AVAILABLE_FROM = ? HOTEL_ID = ? WHERE ID_ROOM = ?");
            query.setParameter(1, room.getNumberOfGuests());
            query.setParameter(2, room.getPrice());
            query.setParameter(3, room.getBreakfastIncluded());
            query.setParameter(4, room.getPetsAllowed());
            query.setParameter(5, room.getDateAvailableFrom());
            query.setParameter(6, room.getHotel());
            query.setParameter(7, id);

            int res = query.executeUpdate();
            System.out.println(res);


            tr.commit();

            System.out.println("Updated");

            return room;

        } catch (HibernateException e) {
            System.err.println("ERROR");
            System.err.println(e.getMessage());
            if (tr != null) tr.rollback();
        } finally {
            if (session != null) session.close();
        }


        return null;
    }


    public List<Room> findRooms(Filter filter) {
        Session session = null;
        Transaction tr = null;
        List<Room> rooms;


        try {

            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();

            Query query = session.createSQLQuery("SELECT * FROM ROOM WHERE NUMBER_OF_GUESTS = ?, PRICE = ? BREAKFAST_INCLUDED = ?, PETS_ALLOWED = ?, DATE_AVAILABLE_FROM = ? HOTEL_ID = ?");
            query.setParameter(1, filter.getNumberOfGuests());
            query.setParameter(2, filter.getPrice());
            query.setParameter(3, filter.isBreakfastIncluded());
            query.setParameter(4, filter.isPetsAllowed());
            query.setParameter(5, filter.getDateAvailableFrom());
            query.setParameter(6, filter.getHotel().getId());

            rooms = query.getResultList();

            tr.commit();

            return rooms;


        } catch (HibernateException e) {
            System.err.println("ERROR");
            System.err.println(e.getMessage());
            if (tr != null) tr.rollback();
        } finally {
            if (session != null) session.close();
        }


        return null;
    }

    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo) {
        UserDAO ud = new UserDAO();

        //Комната считается забронированной, если isBooked - true + обновлена dateAvailableFrom
        //1. получаем из базы комнату по findById +++
        //2. получаем из базы dateAvailableFrom ++
        //3. сравниваем dateAvailableFrom и dateFrom++
        //4. если dateAvailableFrom <= dateFrom --> isBooked = true, setDateAvailableFrom = dateTo +++
        //5. получаем из базы юзера по findById +++
        //6. проверяем залогинен и зареган ли?+++
        //7. создаем новый ордер и передаем его в БД++

        Room room = findById(roomId);
        User user = ud.findById(userId);


        if (room.getDateAvailableFrom().after(dateFrom) && UserSession.isRegistered(user) && UserSession.isLoggedIn(user)) {
            room.setDateAvailableFrom(dateTo);
            RoomSession.bookRoom(room);
            createOrder(room, user, dateFrom, dateTo);
            update(room, roomId);

        }

    }

    public Order createOrder(Room room, User user, Date dateFrom, Date dateTo) {

        Order order = new Order(user, room, dateFrom, dateTo, room.getPrice());
        Session session = null;
        Transaction tr = null;

        try {

            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();

            session.save(order);


            tr.commit();

            return order;


        } catch (HibernateException e) {
            System.err.println("ERROR");
            e.printStackTrace();
            if (tr != null) tr.rollback();
        } finally {
            if (session != null) session.close();
        }

        return null;
    }

    private void cancelReservation(long roomId, long userId){

        Session session = null;
        Transaction tr = null;
        Room room;
        User user;

        try{

            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();

            room = session.get(Room.class, roomId);
            user = session.get(User.class, userId);

            //TODO
            //присвоить dateAvailableFrom начальное значение

            tr.commit();

        } catch(HibernateException e){
            System.err.println("ERROR");
            e.printStackTrace();
            if (tr != null) tr.rollback();
        } finally {
            if (session != null) session.close();
        }


    }


}
