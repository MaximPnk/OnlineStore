package daoImpl;

import dao.TypeDao;
import entity.Type;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TypeDaoImpl implements TypeDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Type findTypeByName(String typeName) {
        Session session = sessionFactory.getCurrentSession();

        Query<Type> typeQuery = session.createQuery("from Type where name=:typeName", Type.class);
        typeQuery.setParameter("typeName", typeName);
        Type type = typeQuery.uniqueResult();

        return type;
    }

    @Override
    public void save(Type type) {
        Session session = sessionFactory.getCurrentSession();

        session.save(type);
    }
}
