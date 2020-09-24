package service;

import dao.OrderDao;
import dao.ProductOrderDao;
import dao.UserDao;
import entity.Order;
import entity.Product;
import entity.ProductOrder;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    OrderDao orderDao;

    @Autowired
    UserDao userDao;

    @Autowired
    ProductOrderDao productOrderDao;

    @Override
    @Transactional
    public void addProductInBasket(Product product) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userDao.findUserByName(name);

        Order order = orderDao.findByUserAndPaid(user, false);
        if (order == null) {
            order = new Order(user, LocalDateTime.now(), false);
            orderDao.save(order);
        }

        ProductOrder productOrder = new ProductOrder(order, product, 1);
        productOrderDao.save(productOrder);
    }


}
