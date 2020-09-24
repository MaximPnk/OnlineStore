package dao;

import entity.ProductOrder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductOrderDaoImpl implements ProductOrderDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void save(ProductOrder productOrder) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(productOrder);
    }
}
