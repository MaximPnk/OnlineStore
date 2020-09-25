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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

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

        ProductOrder productOrder = productOrderDao.findByProductAndOrder(product, order);

        if (productOrder == null) {
            productOrder = new ProductOrder(order, product, 1);
        } else {
            productOrder.setCount(productOrder.getCount()+1);
        }

        BigDecimal sale = new BigDecimal(100).subtract(new BigDecimal(product.getBrand().getSale())).divide(new BigDecimal(100));
        BigDecimal result = product.getPrice().multiply(sale);
        productOrder.setPrice(result.multiply(new BigDecimal(productOrder.getCount())));

        order.add(productOrder);
        productOrderDao.save(productOrder);
    }

    @Override
    @Transactional
    public List<ProductOrder> showCurrent() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userDao.findUserByName(name);

        Order order = orderDao.findByUserAndPaid(user, false);

        if (order == null) {
            return Collections.emptyList();
        }
        List<ProductOrder> productOrders = productOrderDao.getProductsByOrder(order);

        return productOrders;
    }

    @Override
    @Transactional
    public void showCompleted() {
        //получаем список ордеров, в которых пейд=тру и юзер = текущий ИЛИ ничего

        //из таблицы productorder выводим все продукты для каждого ордера
    }


}
