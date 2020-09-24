package dao;

import entity.Role;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public User findUserByName(String userName) {
        Session session = sessionFactory.getCurrentSession();

        Query<User> query = session.createQuery("from User where userName=:name", User.class);
        query.setParameter("name", userName);
        User user = query.uniqueResult();

        return user;

    }

    @Override
    public void save(User user) {
        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.saveOrUpdate(user);
    }
}
