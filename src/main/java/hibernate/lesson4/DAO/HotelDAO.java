package hibernate.lesson4.DAO;

import hibernate.lesson4.model.Hotel;
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
    public List<Hotel> findById(long id) {
        return null;
    }


    @Override
    public void delete(long id) {
        Session session = null;
        Transaction tr = null;

        try{

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

            System.out.print("Deleted");


        } catch (HibernateException e){
            System.err.println("ERROR");
            System.err.println(e.getMessage());
            if (tr != null) tr.rollback();
        } finally {
            if (session != null) session.close();
        }
    }
}
