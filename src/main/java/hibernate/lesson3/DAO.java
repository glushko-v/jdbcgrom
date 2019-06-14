package hibernate.lesson3;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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

    abstract void delete(long id);

    T save(T t) {

        Session session = null;
        Transaction tr = null;

        try {

            session = createSessionFactory().openSession();
            tr = session.getTransaction();

            tr.begin();

            session.save(t);


            tr.commit();

        } catch (HibernateException e) {
            System.err.println("ERROR");
            System.err.println(e.getMessage());
            if (tr != null) tr.rollback();

        } finally {
            if (session != null) session.close();
            System.out.println("Save completed");
        }


        return t;
    }


    abstract T update(T t, long id);


    abstract List<T> findById(long id);


}
