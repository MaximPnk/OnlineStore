import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class InsertDefaultValues {

    public static void main(String[] args) {

        SessionFactory factory = SessionFactoryConfiguration.getSessionFactory("hibernate.cfg.xml");
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            //TODO добавить несколько значений

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }
}
