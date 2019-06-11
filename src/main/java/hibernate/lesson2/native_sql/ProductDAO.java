package hibernate.lesson2.native_sql;

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

    private SessionFactory createSessionFactory() {
        //singleton pattern
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }

        return sessionFactory;
    }

    List<Product> findById(long id){

        Session session = null;
        Transaction tr = null;
        List<Product> products = new ArrayList<>();

        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();

            Query query = session.createSQLQuery("SELECT * from PRODUCT where id = ?").addEntity(Product.class);
            query.setParameter(1, id);
            products = query.getResultList();



            tr.commit();


        }catch (HibernateException e){
            System.err.println("ERROR");
            System.err.println(e.getMessage());
            if (tr != null) tr.rollback();
        } finally {
            if (session != null) session.close();
        }

        System.out.println(products);
        return products;
    }

    List<Product> findByName(String name){

        Session session = null;
        Transaction tr = null;
        List<Product> products = new ArrayList<>();

        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();

            Query query = session.createSQLQuery("SELECT * from PRODUCT where name = ?").addEntity(Product.class);
            query.setParameter(1, name);
            products = query.getResultList();



            tr.commit();


        }catch (HibernateException e){
            System.err.println("ERROR");
            System.err.println(e.getMessage());
            if (tr != null) tr.rollback();
        } finally {
            if (session != null) session.close();
        }

        System.out.println(products);
        return products;
    }

    List<Product> findByContainedName(String name){

        Session session = null;
        Transaction tr = null;
        List<Product> products = new ArrayList<>();

        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();

            Query query = session.createSQLQuery("SELECT * from PRODUCT where name like ?").addEntity(Product.class);
            query.setParameter(1, "%" + name + "%");
            products = query.getResultList();



            tr.commit();


        }catch (HibernateException e){
            System.err.println("ERROR");
            System.err.println(e.getMessage());
            if (tr != null) tr.rollback();
        } finally {
            if (session != null) session.close();
        }

        System.out.println(products);
        return products;
    }

    List<Product> findByPrice(int price, int delta){
        Session session = null;
        Transaction tr = null;
        List<Product> products = new ArrayList<>();

        try{
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();

            Query query = session.createSQLQuery("select * from PRODUCT where PRICE >= ? and PRICE <= ?").addEntity(Product.class);
            query.setParameter(1, price-delta);
            query.setParameter(2, price+delta);
            products = query.getResultList();

            tr.commit();

        }catch (HibernateException e) {
            System.err.println("ERROR");
            System.err.println(e.getMessage());
            if (tr != null) tr.rollback();
        } finally {
            if (session != null) session.close();
        }


        System.out.println(products);
        return products;
    }

    List<Product> findByNameSortedAsc(String name){

        Session session = null;
        Transaction tr = null;
        List<Product> products = new ArrayList<>();

        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();

            Query query = session.createSQLQuery("SELECT * FROM PRODUCT WHERE NAME LIKE ? ORDER BY NAME ASC").addEntity(Product.class);
            query.setParameter(1, "%" + name + "%");
            products = query.getResultList();



            tr.commit();


        }catch (HibernateException e){
            System.err.println("ERROR");
            System.err.println(e.getMessage());
            if (tr != null) tr.rollback();
        } finally {
            if (session != null) session.close();
        }

        System.out.println(products);
        return products;
    }

    List<Product> findByNameSortedDesc(String name){

        Session session = null;
        Transaction tr = null;
        List<Product> products = new ArrayList<>();

        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();

            Query query = session.createSQLQuery("SELECT * FROM PRODUCT WHERE NAME LIKE ? ORDER BY NAME DESC").addEntity(Product.class);
            query.setParameter(1, "%" + name + "%");
            products = query.getResultList();



            tr.commit();


        }catch (HibernateException e){
            System.err.println("ERROR");
            System.err.println(e.getMessage());
            if (tr != null) tr.rollback();
        } finally {
            if (session != null) session.close();
        }

        System.out.println(products);
        return products;
    }


    List<Product> findByPriceSortedDesc(int price, int delta){
        Session session = null;
        Transaction tr = null;
        List<Product> products = new ArrayList<>();

        try{
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();

            Query query = session.createSQLQuery("select * from PRODUCT where PRICE >= ? and PRICE <= ? ORDER BY PRICE DESC").addEntity(Product.class);
            query.setParameter(1, price-delta);
            query.setParameter(2, price+delta);
            products = query.getResultList();

            tr.commit();

        }catch (HibernateException e) {
            System.err.println("ERROR");
            System.err.println(e.getMessage());
            if (tr != null) tr.rollback();
        } finally {
            if (session != null) session.close();
        }


        System.out.println(products);
        return products;
    }


}
