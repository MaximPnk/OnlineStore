package daoImpl;

import dao.StartDao;
import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;

@Repository
public class StartDaoImpl implements StartDao {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void start() {
        Session session = sessionFactory.getCurrentSession();

        Country russia = new Country("Россия");
        Country usa = new Country("США");
        Country china = new Country("Китай");

        Brand lacoste = new Brand("Lacoste", 5, russia);
        Brand tommy = new Brand("Tommy Hilfiger", 30, usa);
        Brand mexx = new Brand("Mexx", 0, china);
        Brand michaelKors = new Brand("Michael Kors", 50, russia);

        lacoste.setCountry(russia);
        tommy.setCountry(usa);
        mexx.setCountry(china);
        michaelKors.setCountry(russia);

        Type jeans = new Type("Джинсы");
        Type sweater = new Type("Свитер");
        Type socks = new Type("Носки");
        Type hat = new Type("Шапка");

        Product product1 = new Product("Носки синие длинные", new BigDecimal(500), 7, lacoste, socks);
        product1.setBrand(lacoste);
        product1.setType(socks);
        Product product2 = new Product("Носки красные короткие", new BigDecimal(250), 14, tommy, socks);
        product2.setBrand(tommy);
        product2.setType(socks);
        Product product3 = new Product("Шапка шерстяная черная", new BigDecimal(2999), 4, mexx, hat);
        product3.setBrand(mexx);
        product3.setType(hat);
        Product product4 = new Product("Шапка хлопковая белая", new BigDecimal(1499), 24, michaelKors, hat);
        product4.setBrand(michaelKors);
        product4.setType(hat);
        Product product5 = new Product("Свитер зеленый, коллекция 2020", new BigDecimal(4999), 3, lacoste, sweater);
        product5.setBrand(lacoste);
        product5.setType(sweater);
        Product product6 = new Product("Свитер коричневый, коллекция 2019", new BigDecimal(999), 1, tommy, sweater);
        product6.setBrand(tommy);
        product6.setType(sweater);
        Product product7 = new Product("Джинсы голубые", new BigDecimal(2999), 45, mexx, jeans);
        product7.setBrand(mexx);
        product7.setType(jeans);
        Product product8 = new Product("Джинсы черные", new BigDecimal(3999), 32, michaelKors, jeans);
        product8.setBrand(michaelKors);
        product8.setType(jeans);
        Product product9 = new Product("Джинсы белые", new BigDecimal(3999), 10, lacoste, jeans);
        product9.setBrand(lacoste);
        product9.setType(jeans);
        Product product10 = new Product("Джинсы с начесом", new BigDecimal(7999), 5, tommy, jeans);
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

        Role admin = new Role("ROLE_ADMIN");
        Role client = new Role("ROLE_CLIENT");
        User adm = new User("admin", passwordEncoder.encode("admin"), "first", "last", "a@a.ru", Collections.singletonList(admin));
        User cli = new User("client", passwordEncoder.encode("client"), "first", "last", "b@b.ru", Collections.singletonList(client));
        session.saveOrUpdate(admin);
        session.saveOrUpdate(client);
        session.saveOrUpdate(adm);
        session.saveOrUpdate(cli);
    }


}
