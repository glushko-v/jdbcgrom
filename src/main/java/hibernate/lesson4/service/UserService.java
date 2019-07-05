package hibernate.lesson4.service;

import hibernate.lesson4.DAO.DAO;
import hibernate.lesson4.DAO.UserDAO;
import hibernate.lesson4.model.User;
import hibernate.lesson4.model.UserSession;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserService extends DAO<User> {

    UserDAO userDAO = new UserDAO();

    @Override
    public User save(User user) {
        return super.save(user);
    }

    @Override
    public void delete(long id) throws Exception {

        if(!UserSession.isRegistered(findById(id))) throw new Exception("User " + id + " has not been registered");
        if(!UserSession.isLoggedIn(findById(id))) throw new Exception("User " + id + " is not logged in");
        userDAO.delete(id);
    }

    @Override
    public User findById(long id) {
        return userDAO.findById(id);
    }

    @Override
    public User update(User user, long id) throws Exception {
        if(!UserSession.isRegistered(findById(id))) throw new Exception("User " + id + " has not been registered");
        if(!UserSession.isLoggedIn(findById(id))) throw new Exception("User " + id + " is not logged in");
        return userDAO.update(user, id);

    }

    @Override
    public SessionFactory createSessionFactory() {
        return super.createSessionFactory();
    }

    public void registerUser(User user) throws Exception {

        if (checkUserName(user.getUserName()))
            throw new Exception("Sorry user with name " + user.getUserName() + " already exists");

        else userDAO.registerUser(user);

    }

    private boolean checkUserName(String userName) {


        Transaction tr = null;
        List<String> names;

        try (Session session = createSessionFactory().openSession()) {


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
        }


        return true;

    }


}
