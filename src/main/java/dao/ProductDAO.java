package dao;

import entity.Product;

import java.util.List;

public interface ProductDAO {

    List<Product> getProducts();

    List<Product> searchProducts(String value);

    void saveProduct(Product product);

    void deleteProduct(int id);
}
