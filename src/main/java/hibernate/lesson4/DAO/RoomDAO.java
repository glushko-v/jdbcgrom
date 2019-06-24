package hibernate.lesson4.DAO;

import hibernate.lesson4.model.Room;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;


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

            Query query = session.createSQLQuery("DELETE FROM ROOM WHERE ID_ROOM =?").addEntity(Room.class);
            query.setParameter(1, id);
            query.executeUpdate();


            tr.commit();

            System.out.print("Deleted");


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
            query.setParameter(3, room.isBreakfastIncluded());
            query.setParameter(4, room.isPetsAllowed());
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
}
