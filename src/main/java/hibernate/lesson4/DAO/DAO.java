package hibernate.lesson4.DAO;

import hibernate.lesson4.model.Hotel;
import hibernate.lesson4.model.Order;
import hibernate.lesson4.model.Room;
import hibernate.lesson4.model.User;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import java.util.List;

public abstract class DAO<T> {

    SessionFactory sessionFactory;



    SessionFactory createSessionFactory() {
        if (sessionFactory == null) {

            sessionFactory = new Configuration().configure().buildSessionFactory();

        }

        return sessionFactory;
    }

    public T save(T t) {

        Session session = null;
        Transaction tr = null;

        try {

            session = createSessionFactory().openSession();

            tr = session.getTransaction();

            tr.begin();

            session.save(t);


            tr.commit();

            System.out.println("Saved");

            return t;

        } catch (HibernateException e) {
            System.err.println("ERROR");
            System.err.println(e.getMessage());
            if (tr != null) tr.rollback();
        } finally {
            if (session != null) session.close();
        }


        return null;
    }

//    public void delete(long id) {
//
//        Session session = null;
//        Transaction tr = null;
//
//
//
//        try {
//
//            session = createSessionFactory().openSession();
//
//            tr = session.getTransaction();
//
//            tr.begin();
//
//            //TODO
//
//            if (entity instanceof Hotel) {
//                Hotel hotel = new Hotel();
//                hotel.setId(id);
//                session.delete(hotel);
//
//            }
//            if (entity instanceof Room) {
//                Room room = new Room();
//                room.setId(id);
//                session.delete(room);
//            }
//            if (entity instanceof User) {
//                User user = new User();
//                user.setId(id);
//                session.delete(user);
//            }
//            if (entity instanceof Order) {
//                Order order = new Order();
//                order.setId(id);
//                session.delete(order);
//            }
//
//
//
//
//            tr.commit();
//
//            System.out.println("Deleted");
//
//        } catch (HibernateException e) {
//            System.err.println("ERROR");
//            System.err.println(e.getMessage());
//            if (tr != null) tr.rollback();
//        } finally {
//            if (session != null) session.close();
//        }
//
//
//    }





//    public void update(long id){
//
//        Session session = null;
//        Transaction tr = null;
//
//
//        try {
//
//            session = createSessionFactory().openSession();
//
//            tr = session.getTransaction();
//
//            tr.begin();
//
//            if (entity instanceof Hotel) {
//                ((Hotel) entity).setId(id);
//
//            }
//            if (entity instanceof Room) {
//                ((Room) entity).setId(id);
//            }
//            if (entity instanceof User) {
//                ((User) entity).setId(id);
//            }
//            if (entity instanceof Order) {
//                ((Order) entity).setId(id);
//            }
//
//            session.update(entity);
//
//
//            tr.commit();
//
//            System.out.println("Updated");
//
//        } catch (HibernateException e) {
//            System.err.println("ERROR");
//            System.err.println(e.getMessage());
//            if (tr != null) tr.rollback();
//        } finally {
//            if (session != null) session.close();
//        }
//
//    }

    public abstract void delete(long id);


    public abstract List<T> findById(long id);




}
