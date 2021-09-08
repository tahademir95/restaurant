package com.application.restaurant.dao.generic;

import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class GenericDaoCustomImpl implements GenericDaoCustom
{

    private EntityManager entityManager;

    @Override
    public Session getSession()
    {
        return entityManager.unwrap(Session.class);
    }

    @Override
    public List findAll(Class clazz)
    {
        return getSession().createCriteria(clazz).list();
    }

    @Override
    public <T> T merge(T transientObject)
    {
        transientObject = (T) getSession().merge(transientObject);
        return transientObject;
    }

    @Override
    public int executeUpdateNativeSql(String nativeSql)
    {
        return getSession().createSQLQuery(nativeSql).executeUpdate();
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }
}
