package dao;

import entity.Product;

import java.util.List;

public interface ProductDao {

    Product getProduct(long id);

    List<Product> getProducts();

    List<Product> searchProducts(String product, String type, String brand, String country);

    void saveProduct(Product product);

    void deleteProduct(long id);

    Product findProductByName(String name);
}
