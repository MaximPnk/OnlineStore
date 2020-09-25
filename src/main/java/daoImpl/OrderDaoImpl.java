package daoImpl;

import dao.OrderDao;
import entity.Order;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import service.UserService;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    UserService userService;

    @Override
    public Order findByUserAndPaid(User user, boolean paid) {
        Session session = sessionFactory.getCurrentSession();

        NativeQuery<Order> query =  session.createSQLQuery("SELECT * FROM orders WHERE user_id=:user AND paid=:paid");
        query.setParameter("user", user.getId());
        query.setParameter("paid", paid);
        Order order = query.addEntity(Order.class).uniqueResult();

        return order;
    }

    @Override
    public void save(Order order) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(order);
    }

    @Override
    public List<Order> findPaidOrders(User user, boolean b) {
        Session session = sessionFactory.getCurrentSession();

        NativeQuery<Order> query =  session.createSQLQuery("SELECT * FROM orders WHERE user_id=:user AND paid=:paid");
        query.setParameter("user", user.getId());
        query.setParameter("paid", b);
        List<Order> orders = query.addEntity(Order.class).getResultList();

        return orders;
    }
}
