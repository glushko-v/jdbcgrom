package hibernate.lesson4.DAO;

import hibernate.lesson4.model.Order;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;

public class OrderDAO extends DAO<Order> {

    @Override
    public SessionFactory createSessionFactory() {
        return super.createSessionFactory();
    }

    @Override
    public Order save(Order order) {
        return super.save(order);
    }

    @Override
    public void delete(long id) {


        Transaction tr = null;

        try (Session session = createSessionFactory().openSession()) {


            tr = session.getTransaction();

            tr.begin();

//            Query query = session.createSQLQuery("DELETE FROM ORDER1 WHERE ID_ORDER =?").addEntity(Order.class);
//            query.setParameter(1, id);
//            query.executeUpdate();

            Order order = session.get(Order.class, id);
            session.delete(order);

            tr.commit();

            System.out.print("Deleted");


        } catch (HibernateException e) {
            System.err.println("ERROR");
            System.err.println(e.getMessage());
            if (tr != null) tr.rollback();
        }

    }

    @Override
    public Order findById(long id) {


        Transaction tr = null;

        try (Session session = createSessionFactory().openSession()) {


            tr = session.getTransaction();

            tr.begin();


            Order order = session.get(Order.class, id);


            tr.commit();

            System.out.println(order);


            return order;


        } catch (HibernateException e) {
            System.err.println("ERROR");
            System.err.println(e.getMessage());
            if (tr != null) tr.rollback();
        }

        return null;
    }

    @Override
    public Order update(Order order, long id) {


        Transaction tr = null;

        try (Session session = createSessionFactory().openSession()) {


            tr = session.getTransaction();

            tr.begin();

//            Order order = session.get(Order.class, id);
            if (order != null) session.update(order);

            tr.commit();

            System.out.println("Updated");
            return order;

        } catch (HibernateException e) {
            System.err.println("ERROR");
            System.err.println(e.getMessage());
            if (tr != null) tr.rollback();
        }

        return null;
    }
}
