package dao;

import entity.Order;
import entity.Product;
import entity.ProductOrder;

import java.util.List;

public interface ProductOrderDao {
    void save(ProductOrder productOrder);

    List<ProductOrder> getProductsByOrder(Order order);

    ProductOrder findByProductAndOrder(Product product, Order order);

    void delete(long productOrderId);
}
