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
    public SessionFactory createSessionFactory() {
        return super.createSessionFactory();
    }

    @Override
    public Room save(Room room) {
        return super.save(room);
    }

    @Override
    public void delete(long id) {


        Transaction tr = null;

        try (Session session = createSessionFactory().openSession()) {


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
        }

    }

    @Override
    public Room findById(long id) {


        Transaction tr = null;

        try (Session session = createSessionFactory().openSession()) {


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
        }


        return null;
    }

    @Override
    public Room update(Room room, long id) {

        Transaction tr = null;

        try (Session session = createSessionFactory().openSession()) {


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
        }


        return null;
    }


    public List<Room> findRooms(Filter filter) {

        Transaction tr = null;
        List<Room> rooms;


        try (Session session = createSessionFactory().openSession()) {


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
        }


        return null;
    }

    public void bookRoom(long roomId, long userId, Date dateFrom, Date dateTo) {
        UserDAO ud = new UserDAO();


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

        Transaction tr = null;

        try (Session session = createSessionFactory().openSession()) {


            tr = session.getTransaction();
            tr.begin();

            session.save(order);


            tr.commit();

            return order;


        } catch (HibernateException e) {
            System.err.println("ERROR");
            e.printStackTrace();
            if (tr != null) tr.rollback();
        }

        return null;
    }

    public void cancelReservation(long roomId, long userId) {


        Transaction tr = null;
        Room room;
        Order order;
        List<Order> orders;

        try (Session session = createSessionFactory().openSession()) {


            tr = session.getTransaction();
            tr.begin();


            Query query = session.createSQLQuery("SELECT * FROM ORDER1 WHERE USER_ID = ?, ROOM_ID = ?").addEntity(Order.class);
            query.setParameter(1, userId);
            query.setParameter(2, roomId);
            orders = query.getResultList();
            order = orders.get(0);
            room = findById(roomId);


            room.setDateAvailableFrom(order.getDateFrom());
            RoomSession.cancelReservation(order.getRoom());


            tr.commit();

        } catch (HibernateException e) {
            System.err.println("ERROR");
            e.printStackTrace();
            if (tr != null) tr.rollback();
        }


    }


}
