package hibernate.lesson4.DAO;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;


public abstract class DAO<T> {

    SessionFactory sessionFactory;


    public SessionFactory createSessionFactory() {
        if (sessionFactory == null) {

            sessionFactory = new Configuration().configure().buildSessionFactory();

        }

        return sessionFactory;
    }

    public T save(T t) {


        Transaction tr = null;

        try (Session session = createSessionFactory().openSession()) {


            tr = session.getTransaction();

            tr.begin();

            session.save(t);


            tr.commit();

            System.out.println("Saved");

            return t;


        } catch (HibernateException h) {
            System.err.println("ERROR");
            h.printStackTrace();
            if (tr != null) tr.rollback();
        }


        return null;
    }


    public abstract void delete(long id) throws Exception;


    public abstract T findById(long id);

    public abstract T update(T t, long id) throws Exception;


}
