package dao;

import entity.Role;

public interface RoleDao {

    Role findRoleByName(String roleName);
}
