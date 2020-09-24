package dao;

import entity.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CountryDaoImpl implements CountryDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Country findCountryByName(String countryName) {
        Session session = sessionFactory.getCurrentSession();

        Query<Country> query = session.createQuery("from Country where name=:countryName", Country.class);
        query.setParameter("countryName", countryName);
        Country country = query.uniqueResult();

        return country;
    }

    @Override
    public void save(Country country) {
        Session session = sessionFactory.getCurrentSession();

        session.save(country);
    }
}
