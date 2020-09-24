package service;

import entity.Product;
import valid.ValidProduct;

import java.util.List;

public interface CrudService {
    void saveProduct(ValidProduct validProduct);

    List<Product> getProducts();

    List<Product> searchProducts(String product, String type, String brand, String country);

    void deleteProduct(long id);

    Product getProduct(long productId);
}
