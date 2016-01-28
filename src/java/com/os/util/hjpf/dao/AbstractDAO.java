/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.util.hjpf.dao;

import com.os.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author mohamed
 */
abstract class AbstractDAO<T> {

    protected abstract String createListHQL(String orderBy, String orderMode, Map filters);

    protected abstract String createCountHQL(String orderBy, String orderMode, Map filters);

    protected abstract String createLoadHQL(Integer id);

    protected T load(Integer id) {
        return uniqueResult(createLoadHQL(id));
    }

    protected T uniqueResult(String hql) {
        T obj = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            obj = (T) session.createQuery(hql).uniqueResult();
            t.commit();
            //   done = true;
        } catch (HibernateException ex) {
            t.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return obj;
    }

    public Long fetchCount(String orderBy, String orderMode, Map filters) {

        Long count = 0L;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            count = (Long) session.createQuery(createCountHQL(orderBy, orderMode, filters)).uniqueResult();

        } catch (HibernateException ex) {
            t.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return count;
    }

    public List<T> list(int first, int pageSize, String orderBy, String orderMode, Map filters) {
        List<T> list = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {

            Query q = session.createQuery(createListHQL(orderBy, orderMode, filters));

            if (first != 0) {
                q.setFirstResult(first);
            }
            if (pageSize != 0) {
                q.setMaxResults(pageSize);
            }

            list = q.list();

        } catch (HibernateException ex) {
            t.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }

        return list;
    }

    protected List list(String hql) {
        List list = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            list = session.createQuery(hql).list();
            //    t.commit();

        } catch (HibernateException ex) {
            t.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    protected List list(String hql, int querySize) {
        List list = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            Query q = session.createQuery(hql);
            if (querySize != 0) {
                q.setMaxResults(querySize);
            }
            list = q.list();
            //    t.commit();

        } catch (HibernateException ex) {
            t.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    protected boolean save(Object model) {
       
        boolean done = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            session.save(model);
            t.commit();
            done = true;
        } catch (HibernateException ex) {
            t.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return done;
    }

    public boolean save(List<Object> models) {
        boolean done = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            for (Object model : models) {
                session.save(model);
                session.flush();
            }
            t.commit();
            done = true;
        } catch (HibernateException ex) {
            t.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return done;
    }

    protected Object saveMerge(Object model) {
       
        boolean done = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            model = session.merge(model);
            t.commit();
            done = true;
        } catch (HibernateException ex) {
            t.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return model;
    }

    protected boolean update(Object model) {
        boolean done = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            session.update(model);
            t.commit();
            done = true;
        } catch (HibernateException ex) {
            t.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return done;
    }

    protected boolean executeUpdate(String hql) {
        boolean done = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            session.createQuery(hql).executeUpdate();
            t.commit();
            done = true;
        } catch (HibernateException ex) {
            t.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return done;
    }

    protected boolean saveOrUpdate(Object model) {
        boolean done = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            session.merge(model);
            t.commit();
            done = true;
        } catch (HibernateException ex) {
            t.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return done;
    }

    protected boolean delete(Object model) {
        boolean done = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            session.delete(model);
            t.commit();
            done = true;
        } catch (HibernateException ex) {
            t.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return done;
    }
}
