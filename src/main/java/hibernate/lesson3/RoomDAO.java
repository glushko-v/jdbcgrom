package hibernate.lesson3;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class RoomDAO extends DAO<Room> {

    @Override
    SessionFactory createSessionFactory() {
        return super.createSessionFactory();
    }

    @Override
    void delete(long id) {

        Session session = null;
        Transaction tr = null;

        try {

            session = createSessionFactory().openSession();
            tr = session.getTransaction();

            tr.begin();

            Room room = new Room();
            room.setId(id);

            session.delete(room);

            tr.commit();


        } catch (HibernateException e) {
            System.err.println("ERROR");
            System.err.println(e.getMessage());
            if (tr != null) tr.rollback();

        } finally {
            if (session != null) session.close();
            System.out.println("Deleted");
        }

    }

    @Override
    Room save(Room room) {
        return super.save(room);
    }

    @Override
    Room update(Room room, long id) {
        Session session = null;
        Transaction tr = null;


        try {

            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();

            room.setId(id);

            session.update(room);


            tr.commit();

            return room;

        } catch (HibernateException e) {

            System.err.println("ERROR");
            System.err.println(e.getMessage());
            if (tr != null) tr.rollback();

        } finally {
            if (session != null) {
                session.close();
                System.out.println("Updated");
            }
        }


        return null;
    }


    @Override
    List<Room> findById(long id) {
        Session session = null;
        Transaction tr = null;


        try {

            session = createSessionFactory().openSession();
            tr = session.getTransaction();

            tr.begin();

            Query query = session.createSQLQuery("SELECT * FROM ROOM WHERE ID = ?").addEntity(Room.class);
            query.setParameter(1, id);
            List<Room> results = query.getResultList();

            tr.commit();

            System.out.println(results);
            return results;


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

