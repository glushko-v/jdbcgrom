package hibernate.lesson3;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;

import java.util.List;

public class HotelDAO extends DAO<Hotel> {

    @Override
    SessionFactory createSessionFactory() {
        return super.createSessionFactory();
    }

    @Override
    Hotel save(Hotel hotel) {
        return super.save(hotel);
    }


    @Override
    Hotel update(Hotel hotel, long id) {
        Session session = null;
        Transaction tr = null;


        try {

            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();

            hotel.setId(id);

            session.update(hotel);


            tr.commit();

            return hotel;

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
    void delete(long id) {
        Session session = null;
        Transaction tr = null;

        try {

            session = createSessionFactory().openSession();
            tr = session.getTransaction();

            tr.begin();

            Hotel hotel = new Hotel();
            hotel.setId(id);

            session.delete(hotel);

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
    List<Hotel> findById(long id) {

        Session session = null;
        Transaction tr = null;


        try {

            session = createSessionFactory().openSession();
            tr = session.getTransaction();

            tr.begin();

            Query query = session.createSQLQuery("SELECT * FROM HOTEL WHERE ID = ?").addEntity(Hotel.class);
            query.setParameter(1, id);
            List<Hotel> results = query.getResultList();


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
