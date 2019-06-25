package hibernate.lesson4.DAO;

import hibernate.lesson4.model.Hotel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;


public class HotelDAO extends DAO<Hotel> {


    @Override
    SessionFactory createSessionFactory() {
        return super.createSessionFactory();
    }

    @Override
    public Hotel save(Hotel hotel) {
        return super.save(hotel);
    }

//    @Override
//    public void delete(long id) {
//        super.delete(id);
//    }
//
//    @Override
//    public void update(long id) {
//        super.update(id);
//    }


    @Override
    public Hotel findById(long id) {

        Session session = null;
        Transaction tr = null;

        try {

            session = createSessionFactory().openSession();
            tr = session.getTransaction();

            tr.begin();


            Hotel hotel = session.get(Hotel.class, id);


            tr.commit();

            System.out.println(hotel);


            return hotel;


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
    public void delete(long id) {
        Session session = null;
        Transaction tr = null;

        try {

            session = createSessionFactory().openSession();
            tr = session.getTransaction();

            tr.begin();

            Query query = session.createSQLQuery("DELETE FROM HOTEL WHERE ID_HOTEL =?").addEntity(Hotel.class);
            query.setParameter(1, id);
            query.executeUpdate();

//            Hotel hotel = session.get(Hotel.class, id);
//
//            session.delete(hotel);


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
    public Hotel update(Hotel hotel, long id) {

        Session session = null;
        Transaction tr = null;

        try {

            session = createSessionFactory().openSession();
            tr = session.getTransaction();

            tr.begin();

            Query query = session.createSQLQuery("UPDATE HOTEL SET COUNTRY = ?, CITY = ?, STREET = ?, NAME = ? WHERE ID_HOTEL = ?");
            query.setParameter(1, hotel.getCountry());
            query.setParameter(2, hotel.getCity());
            query.setParameter(3, hotel.getStreet());
            query.setParameter(4, hotel.getName());
            query.setParameter(5, id);

            int res = query.executeUpdate();
            System.out.println(res);




            tr.commit();

            System.out.println("Updated");

            return hotel;

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
