package hibernate.lesson4.DAO;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;


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







    public abstract void delete(long id);


    public abstract T findById(long id);

    public abstract T update(T t, long id);


}
