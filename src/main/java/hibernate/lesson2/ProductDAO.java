package hibernate.lesson2;

import hibernate.lesson1.HibernateUtils;
import hibernate.lesson1.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductDAO {

    private static SessionFactory sessionFactory;

    public static void main(String[] args) {

        Product product = new Product();
        product.setName("???very old table!");
        product.setDescription("long and wide and black");
        product.setPrice(555);

        Product product1 = new Product();
        product1.setName("very old table111");
        product1.setDescription("long and wide and black");
        product1.setPrice(70);

        Product product2 = new Product();
        product2.setName("very old table222");
        product2.setDescription("long and wide and black");
        product2.setPrice(82);

        Product product3 = new Product();
        product3.setName("very old table333");
        product3.setDescription("long and wide and black");
        product3.setPrice(92);

        List<Product> products = Arrays.asList(product1, product2, product3);

        saveProducts(products);

    }

    public static void save(Product product) {

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

    public static void saveProducts(List<Product> products) {

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

    public static SessionFactory createSessionFactory() {
        //singleton pattern
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }

        return sessionFactory;
    }
}
