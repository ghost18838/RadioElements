package ru.sapteh.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.sapteh.dao.DAO;
import ru.sapteh.model.RadioElement;

import java.util.List;

public class RadioElementService implements DAO<RadioElement,Integer> {
    private SessionFactory sessionFactory;
    public RadioElementService (SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    public List<RadioElement> findByAll() {
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("FROM RadioElement",RadioElement.class).list();
        }
    }

    public RadioElement findById(Integer integer) {
        try(Session session = sessionFactory.openSession()){
            return session.get(RadioElement.class, integer);
        }
    }

    public void create(RadioElement radioElement) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.save(radioElement);
            session.getTransaction().commit();
        }
    }

    public void update(RadioElement radioElement) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.update(radioElement);
            session.getTransaction().commit();
        }
    }

    public void delete(RadioElement radioElement) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.delete(radioElement);
            session.getTransaction().commit();
        }
    }
}
