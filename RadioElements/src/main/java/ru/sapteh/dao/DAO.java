package ru.sapteh.dao;

import java.util.List;

public interface DAO <Entity, Key> {
    List<Entity> findByAll();
    Entity findById (Key key);
    void create(Entity entity);
    void update(Entity entity);
    void delete(Entity entity);
}
