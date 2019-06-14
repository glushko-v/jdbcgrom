package hibernate.lesson2.hql;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private SessionFactory sessionFactory;


    void delete(long id) {

        Session session = null;
        Transaction tr = null;

        try {

            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
            Product product = new Product();
            product.setId(id);
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
            System.err.println("Update is failed");
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

    List<Product> findById(long id) {

        Session session = null;
        Transaction tr = null;
        List<Product> results = new ArrayList<>();

        try {

            session = createSessionFactory().openSession();

            tr = session.getTransaction();

            tr.begin();

            String hql = "FROM Product WHERE id = :id";

            Query query = session.createQuery(hql);
            query.setParameter("id", id);

            results = query.getResultList();




            tr.commit();


        } catch (HibernateException e) {
            System.err.println("ERROR");
            System.err.println(e.getMessage());
            if (tr != null) tr.rollback();
        } finally {
            if (session != null) session.close();
        }

        System.out.println(results);
        return results;
    }

    List<Product> findByName(String name) {
        Session session = null;
        Transaction tr = null;
        List<Product> results = new ArrayList<>();

        try {

            session = createSessionFactory().openSession();

            tr = session.getTransaction();

            tr.begin();

            String hql = "FROM Product WHERE name = :name";

            Query query = session.createQuery(hql);
            query.setParameter("name", name);

            results = query.getResultList();


            tr.commit();


        } catch (HibernateException e) {
            System.err.println("ERROR");
            System.err.println(e.getMessage());
            if (tr != null) tr.rollback();
        } finally {
            if (session != null) session.close();
        }

        System.out.println(results);
        return results;
    }

    List<Product> findByContainedName(String name) {

        Session session = null;
        Transaction tr = null;
        List<Product> results = new ArrayList<>();

        try {

            session = createSessionFactory().openSession();

            tr = session.getTransaction();

            tr.begin();

            String hql = "FROM Product WHERE name like : name";

            Query query = session.createQuery(hql);
            query.setParameter("name", "%" + name + "%");

            results = query.getResultList();


            tr.commit();


        } catch (HibernateException e) {
            System.err.println("ERROR");
            System.err.println(e.getMessage());
            if (tr != null) tr.rollback();
        } finally {
            if (session != null) session.close();
        }

        System.out.println(results);
        return results;


    }

    List<Product> findByPrice(int price, int delta) {

        Session session = null;
        Transaction tr = null;
        List<Product> results = new ArrayList<>();

        try {

            session = createSessionFactory().openSession();

            tr = session.getTransaction();

            tr.begin();


            String hql = "FROM Product WHERE price  <=: price + ?1 AND price >=: price - ?1";

            Query query = session.createQuery(hql);
            query.setParameter("price", price);
            query.setParameter(1, delta);



            results = query.getResultList();


            tr.commit();


        } catch (HibernateException e) {
            System.err.println("ERROR");
            System.err.println(e.getMessage());
            if (tr != null) tr.rollback();
        } finally {
            if (session != null) session.close();
        }

        System.out.println(results);
        return results;


    }

    List<Product> findByNameSortedAsc(String name) {
        Session session = null;
        Transaction tr = null;
        List<Product> results = new ArrayList<>();

        try {

            session = createSessionFactory().openSession();

            tr = session.getTransaction();

            tr.begin();

            String hql = "FROM Product WHERE name like : name ORDER BY name";

            Query query = session.createQuery(hql);
            query.setParameter("name", "%" + name + "%");


            results = query.getResultList();


            tr.commit();


        } catch (HibernateException e) {
            System.err.println("ERROR");
            System.err.println(e.getMessage());
            if (tr != null) tr.rollback();
        } finally {
            if (session != null) session.close();
        }

        System.out.println(results);
        return results;
    }

    List<Product> findByNameSortedDesc(String name) {
        Session session = null;
        Transaction tr = null;
        List<Product> results = new ArrayList<>();

        try {

            session = createSessionFactory().openSession();

            tr = session.getTransaction();

            tr.begin();

            String hql = "FROM Product WHERE name like : name ORDER BY name DESC";

            Query query = session.createQuery(hql);
            query.setParameter("name", "%" + name + "%");


            results = query.getResultList();


            tr.commit();


        } catch (HibernateException e) {
            System.err.println("ERROR");
            System.err.println(e.getMessage());
            if (tr != null) tr.rollback();
        } finally {
            if (session != null) session.close();
        }

        System.out.println(results);
        return results;
    }

    List<Product> findByPriceSortedDesc(int price, int delta) {

        Session session = null;
        Transaction tr = null;
        List<Product> results = new ArrayList<>();

        try {

            session = createSessionFactory().openSession();

            tr = session.getTransaction();

            tr.begin();


            String hql = "FROM Product WHERE price  <=: price + ?1 AND price >=: price - ?1 ORDER BY price DESC";


            Query query = session.createQuery(hql);
            query.setParameter("price", price);
            query.setParameter(1, delta);



            results = query.getResultList();


            tr.commit();


        } catch (HibernateException e) {
            System.err.println("ERROR");
            System.err.println(e.getMessage());
            if (tr != null) tr.rollback();
        } finally {
            if (session != null) session.close();
        }

        System.out.println(results);
        return results;


    }


}
