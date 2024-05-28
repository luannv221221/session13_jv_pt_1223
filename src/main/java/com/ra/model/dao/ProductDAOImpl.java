package com.ra.model.dao;

import com.ra.model.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
@Repository
public class ProductDAOImpl implements ProductDAO{
    private final SessionFactory sessionFactory;
    private int totalPage;
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

    @Override
    public List<Product> findByName(String productName) {
        Session session = sessionFactory.openSession();
        List<Product> products = new ArrayList<>();
        String sql = "SELECT p.productName FROM Product p where p.productName=:name";
        try {
           return session.createQuery(sql).setParameter("name",productName).list();
        }catch (Exception exception){
            exception.printStackTrace();
        } finally {
            session.close();
        }
       return products;
    }

    @Override
    public List<Product> search(String keyword,Integer noPage,Integer limit) {
        Session session = sessionFactory.openSession();
        List<Product> products = new ArrayList<>();
        String hql = "from Product where productName like :keyword";
        try {
            Query query = session.createQuery(hql);
            query.setParameter("keyword","%"+keyword+"%");
            this.totalPage = query.getResultList().size();
            query.setFirstResult((noPage -1 )*limit);
            query.setMaxResults(limit);
            products = query.getResultList();

        }catch (Exception exception){
            exception.printStackTrace();
        } finally {
            session.close();
        }
        return products;
    }

    @Override
    public List<Product> pagination(Integer noPage, Integer limit) {
        Session session = sessionFactory.openSession();
        List<Product> products = new ArrayList<>();
        try {
            Query query = session.createQuery("from Product ");
            this.totalPage = query.getResultList().size();
            query.setFirstResult((noPage -1 )*limit);
            query.setMaxResults(limit);
            products = query.getResultList();
        } catch (Exception exception){
            exception.printStackTrace();
        } finally {
            session.close();
        }
        return products;
    }
    @Override
    public int getTotalPage(){
        return this.totalPage;
    }
}
