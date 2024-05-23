package com.ra.model.dao;

import com.ra.model.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class ProductDAOImpl implements ProductDAO{
    private final SessionFactory sessionFactory;
    @Autowired
    public ProductDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Product> findAll() {
        Session session = sessionFactory.openSession();
        List<Product> products = new ArrayList<>();
        try {
            products = session.createQuery("from Product ",Product.class).list();
        } catch (Exception exception){
                exception.printStackTrace();
        } finally {
            session.close();
        }
        return products;
    }

    @Override
    public Boolean saveOrUpdate(Product product) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
            return true;
        } catch (Exception exception){
            exception.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return false;
    }
}
