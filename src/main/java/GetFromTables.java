import entity.Brand;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class GetFromTables {

    public static void main(String[] args) {

        SessionFactory factory = SessionFactoryConfiguration.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            Brand brand = session.get(Brand.class, 2);
            System.out.println(brand);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }
}
