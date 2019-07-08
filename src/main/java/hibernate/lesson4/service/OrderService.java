package hibernate.lesson4.service;

import hibernate.lesson4.DAO.DAO;
import hibernate.lesson4.DAO.OrderDAO;
import hibernate.lesson4.model.Order;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.List;

public class OrderService {
    OrderDAO orderDAO = new OrderDAO();

    SessionFactory sessionFactory;

    SessionFactory createSessionFactory() {
        if (sessionFactory == null) {

            sessionFactory = new Configuration().configure().buildSessionFactory();

        }

        return sessionFactory;
    }

    public Order save(Order order) throws Exception {

        if (!isOrderExists(order)) return orderDAO.save(order);
        else throw new Exception("Sorry, order " + order.getId() + " already exists");
    }

    private boolean isOrderExists(Order order) {

        Transaction tr = null;
        List<Order> orders;

        try (Session session = createSessionFactory().openSession()) {


            tr = session.getTransaction();

            tr.begin();

            Query query = session.createSQLQuery("SELECT * FROM ORDER1").addEntity(Order.class);
            orders = query.getResultList();

            for (Order orderFromDB : orders) {
                if (order.equals(orderFromDB)) return true;

            }

            tr.commit();


        } catch (HibernateException e) {
            System.err.println("ERROR");
            e.printStackTrace();
            if (tr != null) tr.rollback();
        }

        return false;


    }

    public void delete(long id){

        orderDAO.delete(id);
    }

    public Order update(Order order, long id) throws Exception {
        if (isOrderExists(order)) return orderDAO.update(order, id);
        else throw new Exception("Can not find order " + order.getId());
    }

    public Order findById(long id){
        return orderDAO.findById(id);
    }



}

