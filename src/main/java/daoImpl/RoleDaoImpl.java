package daoImpl;

import dao.RoleDao;
import entity.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Role findRoleByName(String roleName) {
        Session session = sessionFactory.getCurrentSession();

        Query<Role> query = session.createQuery("from Role where name=:name", Role.class);
        query.setParameter("name", roleName);
        Role role = query.uniqueResult();

        return role;
    }
}
