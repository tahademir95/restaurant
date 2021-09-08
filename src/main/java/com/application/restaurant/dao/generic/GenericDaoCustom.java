package com.application.restaurant.dao.generic;

import org.hibernate.Session;

import java.util.List;

public interface GenericDaoCustom
{
    public Session getSession();

    public List findAll(Class clazz);

    public <T> T merge(T transientObject);

    public int executeUpdateNativeSql(String nativeSql);
}
