package service;

import dao.BrandDao;
import dao.CountryDao;
import dao.ProductDao;
import dao.TypeDao;
import entity.Brand;
import entity.Country;
import entity.Product;
import entity.Type;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import valid.ValidProduct;

import java.util.List;

@Service
public class CrudServiceImpl implements CrudService {

    @Autowired
    ProductDao productDao;

    @Autowired
    TypeDao typeDao;

    @Autowired
    BrandDao brandDao;

    @Autowired
    CountryDao countryDao;

    @Override
    @Transactional
    public void saveValidProduct(ValidProduct validProduct) {

        Country country = countryDao.findCountryByName(validProduct.getCountryName());

        if (country == null) {
            country = new Country(validProduct.getCountryName());
            countryDao.save(country);
        }

        Brand brand = brandDao.findBrandByNameAndSale(validProduct.getBrandName());

        if (brand == null) {
            brand = new Brand(validProduct.getBrandName(), validProduct.getBrandSale());
            brand.setCountry(country);
            brandDao.save(brand);
        } else {
            brand.setCountry(country);
            brand.setSale(validProduct.getBrandSale());
        }

        Type type = typeDao.findTypeByName(validProduct.getTypeName());

        if (type == null) {
            type = new Type(validProduct.getTypeName());
            typeDao.save(type);
        }

        Product product = productDao.findProductByName(validProduct.getName());

        if (product == null) {
            product = new Product(validProduct.getName(), validProduct.getPrice(), validProduct.getAmount());
            product.setBrand(brand);
            product.setType(type);
            productDao.saveProduct(product);
        } else {
            product.setBrand(brand);
            product.setType(type);
            product.setAmount(validProduct.getAmount());
            product.setPrice(validProduct.getPrice());
        }
    }

    @Override
    @Transactional
    public List<Product> getProducts() {
        return productDao.getProducts();
    }

    @Override
    @Transactional
    public List<Product> searchProducts(String product, String type, String brand, String country) {
        return productDao.searchProducts(product, type, brand, country);
    }

    @Override
    @Transactional
    public void deleteProduct(long id) {
        productDao.deleteProduct(id);
    }

    @Override
    @Transactional
    public Product getProduct(long productId) {
        return productDao.getProduct(productId);
    }
}
