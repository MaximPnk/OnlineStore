import entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryConfiguration {

    public static SessionFactory getSessionFactory(String cfg) {

        SessionFactory factory = new Configuration()
                .configure(cfg)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Type.class)
                .addAnnotatedClass(Brand.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(Sale.class)
                .addAnnotatedClass(Order.class)
                .buildSessionFactory();

        return factory;
    }
}
