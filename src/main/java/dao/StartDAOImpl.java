package dao;

import entity.Brand;
import entity.Country;
import entity.Product;
import entity.Type;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Repository
public class StartDAOImpl implements StartDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    @Transactional
    public void start() {
        Session session = sessionFactory.getCurrentSession();

        Country russia = new Country("Россия");
        Country usa = new Country("США");
        Country china = new Country("Китай");

        Brand lacoste = new Brand("Lacoste", 5);
        Brand tommy = new Brand("Tommy Hilfiger", 30);
        Brand mexx = new Brand("Mexx", 0);
        Brand michaelKors = new Brand("Michael Kors", 50);

        lacoste.setCountry(russia);
        tommy.setCountry(usa);
        mexx.setCountry(china);
        michaelKors.setCountry(russia);

        Type jeans = new Type("Джинсы");
        Type sweater = new Type("Свитер");
        Type socks = new Type("Носки");
        Type hat = new Type("Шапка");

        Product product1 = new Product("Носки синие длинные", new BigDecimal(500), 7);
        product1.setBrand(lacoste);
        product1.setType(socks);
        Product product2 = new Product("Носки красные короткие", new BigDecimal(250), 14);
        product2.setBrand(tommy);
        product2.setType(socks);
        Product product3 = new Product("Шапка шерстяная черная", new BigDecimal(2999), 4);
        product3.setBrand(mexx);
        product3.setType(hat);
        Product product4 = new Product("Шапка хлопковая белая", new BigDecimal(1499), 24);
        product4.setBrand(michaelKors);
        product4.setType(hat);
        Product product5 = new Product("Свитер зеленый, коллекция 2020", new BigDecimal(4999), 3);
        product5.setBrand(lacoste);
        product5.setType(sweater);
        Product product6 = new Product("Свитер коричневый, коллекция 2019", new BigDecimal(999), 1);
        product6.setBrand(tommy);
        product6.setType(sweater);
        Product product7 = new Product("Джинсы голубые", new BigDecimal(2999), 45);
        product7.setBrand(mexx);
        product7.setType(jeans);
        Product product8 = new Product("Джинсы черные", new BigDecimal(3999), 32);
        product8.setBrand(michaelKors);
        product8.setType(jeans);
        Product product9 = new Product("Джинсы белые", new BigDecimal(3999), 10);
        product9.setBrand(lacoste);
        product9.setType(jeans);
        Product product10 = new Product("Джинсы с начесом", new BigDecimal(7999), 5);
        product10.setBrand(tommy);
        product10.setType(jeans);

        session.saveOrUpdate(russia);
        session.saveOrUpdate(usa);
        session.saveOrUpdate(china);
        session.saveOrUpdate(lacoste);
        session.saveOrUpdate(tommy);
        session.saveOrUpdate(mexx);
        session.saveOrUpdate(michaelKors);
        session.saveOrUpdate(jeans);
        session.saveOrUpdate(sweater);
        session.saveOrUpdate(socks);
        session.saveOrUpdate(hat);
        session.saveOrUpdate(product1);
        session.saveOrUpdate(product2);
        session.saveOrUpdate(product3);
        session.saveOrUpdate(product4);
        session.saveOrUpdate(product5);
        session.saveOrUpdate(product6);
        session.saveOrUpdate(product7);
        session.saveOrUpdate(product8);
        session.saveOrUpdate(product9);
        session.saveOrUpdate(product10);
    }


}
