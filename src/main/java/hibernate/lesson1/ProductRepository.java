package hibernate.lesson1;

import org.hibernate.Session;

public class ProductRepository {

    void save(Product product){

        Session session = new HibernateUtils().createSessionFactory().openSession();

        session.getTransaction().begin();

        session.save(product);

        session.getTransaction().commit();

        System.out.println("Saved");

        session.close();

    }

    void delete(long id){

        Session session = new HibernateUtils().createSessionFactory().openSession();

        session.getTransaction().begin();

        
        Product product = new Product();

        product.setId(id);


        session.delete(product);

        session.getTransaction().commit();

        System.out.println("Deleted");

        session.close();

    }

    void update(Product product){

        Session session = new HibernateUtils().createSessionFactory().openSession();

        session.getTransaction().begin();

        session.update(product);

        session.getTransaction().commit();

        System.out.println("Updated");

        session.close();

    }
}
