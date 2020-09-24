package dao;

import entity.Type;

public interface TypeDao {
    Type findTypeByName(String typeName);

    void save(Type type);
}
