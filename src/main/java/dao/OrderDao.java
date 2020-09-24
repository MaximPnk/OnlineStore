package dao;

import entity.Order;
import entity.User;

public interface OrderDao {

     Order findByUserAndPaid(User user, boolean paid);

     void save(Order order);
}
