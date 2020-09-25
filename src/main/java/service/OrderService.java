package service;

import entity.Order;
import entity.Product;
import entity.ProductOrder;

import java.util.List;

public interface OrderService {

    void addProductInBasket(Product product);

    List<ProductOrder> showCurrent();

    List<Order> showCompleted();

    void buyProducts();

    void deleteProductOrder(long productOrderId);
}
