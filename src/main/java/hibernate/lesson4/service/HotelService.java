package hibernate.lesson4.service;

import hibernate.lesson4.model.Hotel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class HotelService {

    //1. При save сохраняется отель, который уже есть в базе
    //2. Нет пустых полей

    //TODO


    SessionFactory sessionFactory;


    SessionFactory createSessionFactory() {
        if (sessionFactory == null) {

            sessionFactory = new Configuration().configure().buildSessionFactory();

        }

        return sessionFactory;
    }

    public boolean isHotelExists(Hotel hotel) {

        List<Hotel> hotels;

        Session session = null;
        Transaction tr = null;

        try {

            session = createSessionFactory().openSession();
            tr = session.getTransaction();

            tr.begin();

            Query query = session.createSQLQuery("SELECT * FROM HOTEL").addEntity(Hotel.class);
            hotels = query.getResultList();

            for (Hotel hotelFromDB: hotels) {
                if (hotel.equals(hotelFromDB)) return true;

            }

            tr.commit();

            return false;




        } catch (HibernateException e) {
            System.err.println("ERROR");
            e.printStackTrace();
            if (tr != null) tr.rollback();
        } finally {
            if (session != null) session.close();
        }

        return false;


    }


}
