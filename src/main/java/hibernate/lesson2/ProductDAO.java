package hibernate.lesson2;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class ProductDAO {

    private SessionFactory sessionFactory;


    void delete(Product product) {

        Session session = null;
        Transaction tr = null;

        try {

            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
            session.delete(product);
            tr.commit();


        } catch (HibernateException e) {
            System.err.println("Delete is failed");
            System.err.println(e.getMessage());
            if (tr != null) tr.rollback();
        } finally {
            if (session != null) {
                session.close();
            }

        }

    }

    public void save(Product product) {

        Session session = null;
        Transaction tr = null;

        try {

            session = createSessionFactory().openSession();

            tr = session.getTransaction();
            tr.begin();
            session.save(product);


            tr.commit();


        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.err.println(e.getMessage());
            if (tr != null) tr.rollback();
        } finally {
            if (session != null) session.close();
        }

        System.out.println("Save is done");
    }


    void update(Product product) {

        Session session = null;
        Transaction tr = null;

        try {

            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
            
            session.update(product);
            tr.commit();


        } catch (HibernateException e) {
            System.err.println("Delete is failed");
            System.err.println(e.getMessage());
            if (tr != null) tr.rollback();
        } finally {
            if (session != null) {
                session.close();
            }

        }

    }


    public void saveProducts(List<Product> products) {

        Session session = null;
        Transaction tr = null;

        try {

            session = createSessionFactory().openSession();

            tr = session.getTransaction();

            tr.begin();

            for (Product product : products) {

                session.save(product);
            }

            tr.commit();


        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.err.println(e.getMessage());
            if (tr != null) tr.rollback();
        } finally {
            if (session != null) session.close();
        }

        System.out.println("Save is done");


    }

    public SessionFactory createSessionFactory() {
        //singleton pattern
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }

        return sessionFactory;
    }


}
