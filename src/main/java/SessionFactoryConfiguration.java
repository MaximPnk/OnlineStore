import entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class SessionFactoryConfiguration {

    public static SessionFactory getSessionFactory() {

        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream("src/main/resources/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Configuration configuration = new Configuration();
        configuration = configuration
                .setProperty("hibernate.connection.driver_class", properties.getProperty("driver"))
                .setProperty("hibernate.connection.url", properties.getProperty("url"))
                .setProperty("hibernate.connection.username", properties.getProperty("user"))
                .setProperty("hibernate.connection.password", properties.getProperty("pass"))
                .setProperty("hibernate.connection.pool_size", properties.getProperty("pool"))
                .setProperty("hibernate.dialect", properties.getProperty("dialect"))
                .setProperty("hibernate.show_sql", properties.getProperty("show"))
                .setProperty("hibernate.current_session_context_class", properties.getProperty("session_context"))
                .setProperty("hibernate.hbm2ddl.auto", properties.getProperty("hbm2ddl"))
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Type.class)
                .addAnnotatedClass(Brand.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(Sale.class)
                .addAnnotatedClass(Order.class);


        return configuration.buildSessionFactory();
    }
}
