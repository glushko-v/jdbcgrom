package hibernate.lesson4.DAO;

import hibernate.lesson4.model.User;
import hibernate.lesson4.model.UserSession;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;


public class UserDAO extends DAO<User> {

    @Override
    SessionFactory createSessionFactory() {
        return super.createSessionFactory();
    }

    @Override
    public User save(User user) {
        return super.save(user);
    }

    @Override
    public void delete(long id) {

        Session session = null;
        Transaction tr = null;

        try {

            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();

            Query query = session.createSQLQuery("DELETE FROM USER1 WHERE ID_USER = ?").addEntity(User.class);
            query.setParameter(1, id);
            query.executeUpdate();


//            User user = session.get(User.class, id);
//            session.delete(user);

            tr.commit();

            System.out.println("Deleted");

        } catch (HibernateException e) {
            System.err.println("ERROR");
            System.err.println(e.getMessage());
            if (tr != null) tr.rollback();
        } finally {
            if (session != null) session.close();
        }

    }

    @Override
    public User findById(long id) {

        Session session = null;
        Transaction tr = null;

        try {

            session = createSessionFactory().openSession();
            tr = session.getTransaction();

            tr.begin();


            User user = session.get(User.class, id);


            tr.commit();

            System.out.println(user);


            return user;


        } catch (HibernateException e) {
            System.err.println("ERROR");
            System.err.println(e.getMessage());
            if (tr != null) tr.rollback();
        } finally {
            if (session != null) session.close();
        }

        return null;
    }

    @Override
    public User update(User user, long id) {
        Session session = null;
        Transaction tr = null;

        try {

            session = createSessionFactory().openSession();
            tr = session.getTransaction();

            tr.begin();

//            User user = session.get(User.class, id);
            session.update(user);

            tr.commit();

            System.out.println("Updated");
            return user;

        } catch (HibernateException e) {
            System.err.println("ERROR");
            System.err.println(e.getMessage());
            if (tr != null) tr.rollback();
        } finally {
            if (session != null) session.close();
        }


        return null;
    }

    public void registerUser(User user) throws Exception {

        if (checkUserName(user.getUserName()))
            throw new Exception("Sorry user with name " + user.getUserName() + " already exists");
        else {

            UserSession.registerUser(user);
            save(user);

        }
    }

    private boolean checkUserName(String userName) {

        Session session = null;
        Transaction tr = null;
        List<String> names;

        try {

            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();


            Query query = session.createSQLQuery("SELECT * FROM USER1 WHERE USER_NAME = ?").addEntity(User.class);
            query.setParameter(1, userName);

            names = query.getResultList();
            int res = names.size();


            tr.commit();

            return (res != 0);


        } catch (HibernateException e) {
            System.err.println("ERROR");
            e.printStackTrace();
            if (tr != null) tr.rollback();
        } finally {
            if (session != null) session.close();
        }


        return true;

    }

    public void login(String userName, String password) throws Exception {

        Session session = null;
        Transaction tr = null;
        List<User> users;
        User user;

        try {

            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();

            Query query = session.createSQLQuery("SELECT * FROM USER1 WHERE USER_NAME = ?").addEntity(User.class);
            query.setParameter(1, userName);
            users = query.getResultList();
            if (users.size() == 1) user = users.get(0);
            else throw new Exception("Sorry, user " + userName + " doesn't exist");

            if (user.getPassword().equals(password)) {
                UserSession.logIn(user);
                System.out.println("User " + user.getId() + " " + user.getUserName() + " successfully logged in");
            }


            tr.commit();

        } catch (HibernateException e) {
            System.err.println("ERROR");
            e.printStackTrace();
            if (tr != null) tr.rollback();
        } finally {
            if (session != null) session.close();
        }

    }

    public void logOut(User user) {

        UserSession.logOut(user);

    }


}
