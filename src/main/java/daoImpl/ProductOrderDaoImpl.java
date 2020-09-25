package daoImpl;

import dao.ProductOrderDao;
import entity.Order;
import entity.Product;
import entity.ProductOrder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductOrderDaoImpl implements ProductOrderDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void save(ProductOrder productOrder) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(productOrder);
    }

    @Override
    public List<ProductOrder> getProductsByOrder(Order order) {
        Session session = sessionFactory.getCurrentSession();

        NativeQuery<ProductOrder> query1 = session.createSQLQuery("select * from order_product where order_id=:orderId");
        query1.setParameter("orderId", order.getId());
        List<ProductOrder> productOrders = query1.addEntity(ProductOrder.class).getResultList();

        return productOrders;
    }

    @Override
    public ProductOrder findByProductAndOrder(Product product, Order order) {
        Session session = sessionFactory.getCurrentSession();

        NativeQuery<ProductOrder> query1 = session.createSQLQuery("select * from order_product where order_id=:orderId and product_id =:productId");
        query1.setParameter("orderId", order.getId());
        query1.setParameter("productId", product.getId());
        ProductOrder productOrder = query1.addEntity(ProductOrder.class).uniqueResult();

        return productOrder;
    }

    @Override
    public void delete(long productOrderId) {
        Session session = sessionFactory.getCurrentSession();

        ProductOrder po = session.get(ProductOrder.class, productOrderId);

        session.delete(po);
    }
}
