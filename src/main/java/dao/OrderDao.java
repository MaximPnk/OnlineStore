package dao;

import entity.Order;
import entity.User;

import java.util.List;

public interface OrderDao {

     Order findByUserAndPaid(User user, boolean paid);

     void save(Order order);

    List<Order> findPaidOrders(User user, boolean b);
}
