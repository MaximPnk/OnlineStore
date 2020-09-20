import entity.Brand;
import entity.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class InsertDefaultValues {

    public static void main(String[] args) {

        SessionFactory factory = SessionFactoryConfiguration.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            //TODO добавить несколько значений
            Country country = new Country("Russia");
            Brand brand = new Brand("Armani", 30);
            brand.setCountry(country);

            session.save(brand);
            session.save(country);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }
}
