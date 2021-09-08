package com.application.restaurant.service;


import com.application.restaurant.dao.generic.GenericDaoCustom;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public abstract class BaseService
{
    protected abstract GenericDaoCustom getDao();
}
