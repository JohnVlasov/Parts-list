package ru.vlae.computerParts.dao;

import org.hibernate.query.Query;
import ru.vlae.computerParts.model.Part;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PartsDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    public List<Part> allParts(int page) {  // получение списка деталей по 10 шт. на страницу
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from ru.vlae.computerParts.model.Part");
        return query.setFirstResult(10 * (page - 1)).setMaxResults(10).list();
    }

    @SuppressWarnings("unchecked")
    public int countOfComputers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select min(quantity) from Part where need = true", Number.class).getSingleResult().intValue();
    }

    @SuppressWarnings("unchecked")
    public List<Part> allParts(int page, String needSelect, String searchName) {
        String q;
        Session session = sessionFactory.getCurrentSession();
        Query query;
        switch (needSelect) {
            case "need":
                q = "from Part where need = true AND name LIKE :nameX";
                break;
            case "notNeed":
                q = "from Part where need = false AND name LIKE :nameX";
                break;
            default:
                q = "from Part where name LIKE :nameX";
                break;
        }
        query = session.createQuery(q);
        query.setParameter("nameX", "%" + searchName + "%");
        return query.setFirstResult(10 * (page - 1)).setMaxResults(10).list();
    }

    public boolean isPartExist(String searchName) {
        Session session = sessionFactory.getCurrentSession();
        String q = "select count(*) from Part where name = :nameX";
        int count = session.createQuery(q, Number.class).setParameter("nameX", searchName).getSingleResult().intValue();
        return (count>0);
    }

    public int partCount(String needSelect, String searchName) {
        String q;
        Session session = sessionFactory.getCurrentSession();
        Query query;
        switch (needSelect) {
            case "need":
                q = "select count(*) from Part where need = true AND name LIKE :nameX";
                break;
            case "notNeed":
                q = "select count(*) from Part where need = false AND name LIKE :nameX";
                break;
            default:
                q = "select count(*) from Part where name LIKE :nameX";
                break;
        }
        return session.createQuery(q, Number.class).setParameter("nameX", "%" + searchName + "%").getSingleResult().intValue();
    }

    public void add(Part part) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(part);
    }

    public void delete(Part part) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(part);
    }

    public void edit(Part part) {
        Session session = sessionFactory.getCurrentSession();
        session.update(part);
    }

    public Part getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Part.class, id);
    }
}