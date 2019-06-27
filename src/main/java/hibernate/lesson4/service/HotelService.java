package hibernate.lesson4.service;

import hibernate.lesson4.DAO.HotelDAO;
import hibernate.lesson4.model.Hotel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.List;

public class HotelService {

    //1. При save сохраняется отель, который уже есть в базе
    //2. Есть пустые поля



    private SessionFactory sessionFactory;
    private List<Hotel> hotels;
    private HotelDAO hotelDAO = new HotelDAO();


    SessionFactory createSessionFactory() {
        if (sessionFactory == null) {

            sessionFactory = new Configuration().configure().buildSessionFactory();

        }

        return sessionFactory;
    }

    private boolean isHotelExists(Hotel hotel) {


        Session session = null;
        Transaction tr = null;

        try {

            session = createSessionFactory().openSession();
            tr = session.getTransaction();

            tr.begin();

            Query query = session.createSQLQuery("SELECT * FROM HOTEL").addEntity(Hotel.class);
            hotels = query.getResultList();

            for (Hotel hotelFromDB : hotels) {
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

    private boolean isNullFields(Hotel hotel) {

        return (hotel.getCity() == null || hotel.getCountry() == null || hotel.getStreet() == null || hotel.getName() == null);


    }

    private boolean checkHotel(Hotel hotel){

        return (isHotelExists(hotel) && isNullFields(hotel));

    }

    public Hotel save(Hotel hotel){

        if (!checkHotel(hotel)) return hotelDAO.save(hotel);

        return null;
    }






}
