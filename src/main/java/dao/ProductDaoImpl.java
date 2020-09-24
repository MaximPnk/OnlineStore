package dao;

import entity.Brand;
import entity.Country;
import entity.Product;
import entity.Type;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Product getProduct(long id) {
        Session session = sessionFactory.getCurrentSession();

        Product product = session.get(Product.class, id);

        return product;
    }

    @Override
    public List<Product> getProducts() {
        Session session = sessionFactory.getCurrentSession();

        Query<Product> query = session.createQuery("from Product ", Product.class);
        List<Product> products = query.getResultList();

        return products;
    }

    @Override
    public List<Product> searchProducts(String product, String type, String brand, String country) {
        Session currentSession = sessionFactory.getCurrentSession();

        String productSearch = product == null || product.trim().isEmpty() ? "" : "and lower(name) like '%" + product.toLowerCase() + "%' ";
        String typeSearch = type == null || type.trim().isEmpty() ? "" : "and lower(type.name) like '%" + type.toLowerCase() + "%' ";
        String brandSearch = brand == null || brand.trim().isEmpty() ? "" : "and lower(brand.name) like '%" + brand.toLowerCase() + "%' ";
        String countrySearch = country == null || country.trim().isEmpty() ? "" : "and lower(brand.country.name) like '%" + country.toLowerCase() + "%'";

        String search = "from Product where 1 = 1 " + productSearch + typeSearch + brandSearch + countrySearch;

        List<Product> products = currentSession.createQuery(search, Product.class).getResultList();

        return products;
    }

    @Override
    public void deleteProduct(long id) {
        Session session = sessionFactory.getCurrentSession();

        Product product = session.get(Product.class, id);

        session.delete(product);
    }

    @Override
    public Product findProductByName(String name) {
        Session session = sessionFactory.getCurrentSession();

        Query<Product> productQuery = session.createQuery("from Product where name=:productName", Product.class);
        productQuery.setParameter("productName", name);
        Product product = productQuery.uniqueResult();

        return product;
    }

    @Override
    public void saveProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();

        session.save(product);
    }
}
