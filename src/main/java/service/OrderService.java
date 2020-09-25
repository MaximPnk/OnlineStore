package service;

import entity.Product;
import entity.ProductOrder;

import java.util.List;

public interface OrderService {

    void addProductInBasket(Product product);

    List<ProductOrder> showCurrent();

    void showCompleted();
}
