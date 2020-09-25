package daoImpl;

import dao.BrandDao;
import entity.Brand;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BrandDaoImpl implements BrandDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Brand findBrandByNameAndSale(String brandName) {
        Session session = sessionFactory.getCurrentSession();

        Query<Brand> query = session.createQuery("from Brand where name=:brandName", Brand.class);
        query.setParameter("brandName", brandName);
        Brand brand = query.uniqueResult();

        return brand;
    }

    @Override
    public void save(Brand brand) {
        Session session = sessionFactory.getCurrentSession();

        session.save(brand);
    }
}
