package dao;

import entity.Order;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import service.UserService;

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
}
