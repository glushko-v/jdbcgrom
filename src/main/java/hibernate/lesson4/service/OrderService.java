package hibernate.lesson4.service;

import hibernate.lesson4.DAO.DAO;
import hibernate.lesson4.DAO.OrderDAO;
import hibernate.lesson4.model.Order;
import org.hibernate.SessionFactory;

public class OrderService extends DAO<Order> {
    OrderDAO orderDAO = new OrderDAO();

    @Override
    public SessionFactory createSessionFactory() {
        return super.createSessionFactory();
    }

    @Override
    public Order save(Order order) {
        return super.save(order);
    }

    @Override
    public void delete(long id) throws Exception {

        orderDAO.delete(id);

    }

    @Override
    public Order findById(long id) {
        return orderDAO.findById(id);
    }

    @Override
    public Order update(Order order, long id) throws Exception {
        return orderDAO.update(order, id);
    }
}
