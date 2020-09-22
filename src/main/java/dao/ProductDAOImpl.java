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
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Product> getProducts() {
        Session session = sessionFactory.getCurrentSession();

        Query<Product> query = session.createQuery("from Product ", Product.class);
        List<Product> products = query.getResultList();

        return products;
    }

    @Override
    @Transactional
    public List<Product> searchProducts(String value) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query theQuery;
        if (value != null && value.trim().length() > 0) {
            theQuery = currentSession.createQuery("from Product where lower(name) like :theValue or lower(brand.name) like :theValue or lower(brand.country.name) like :theValue or lower(type.name) like :theValue", Product.class);
            theQuery.setParameter("theValue", "%" + value.toLowerCase() + "%");
        }
        else {
            theQuery =currentSession.createQuery("from Product ", Product.class);
        }
        List<Product> products = theQuery.getResultList();

        return products;
    }

    @Override
    @Transactional
    public void saveProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();

        Query<Country> countryQuery = session.createQuery("from Country where name=:countryName", Country.class);
        countryQuery.setParameter("countryName", product.getBrand().getCountry().getName());
        Country country = countryQuery.uniqueResult();
        if (country == null) {
            country = product.getBrand().getCountry();
        }
        session.saveOrUpdate(country);

        Query<Brand> brandQuery = session.createQuery("from Brand where name=:brandName and sale=:brandSale", Brand.class);
        brandQuery.setParameter("brandName", product.getBrand().getName());
        brandQuery.setParameter("brandSale", product.getBrand().getSale());
        Brand brand = brandQuery.uniqueResult();
        if (brand == null) {
            brand = product.getBrand();
            brand.setCountry(country);
        }
        session.saveOrUpdate(brand);

        Query<Type> typeQuery = session.createQuery("from Type where name=:typeName", Type.class);
        typeQuery.setParameter("typeName", product.getType().getName());
        Type type = typeQuery.uniqueResult();
        if (type == null) {
            type = product.getType();
        }
        session.saveOrUpdate(type);

        Query<Product> productQuery = session.createQuery("from Product where name=:productName", Product.class);
        productQuery.setParameter("productName", product.getName());
        Product newProduct = productQuery.uniqueResult();
        if (newProduct != null) {
            newProduct.setPrice(product.getPrice());
            newProduct.setAmount(product.getAmount()+newProduct.getAmount());
        } else {
            newProduct = product;
        }
        newProduct.setBrand(brand);
        newProduct.setType(type);
        session.save(newProduct);
    }

    @Override
    @Transactional
    public void deleteProduct(int id) {
        Session session = sessionFactory.getCurrentSession();

        Product product = session.get(Product.class, id);
        session.delete(product);
    }
}
