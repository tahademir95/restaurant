package com.application.restaurant.service.productType.impl;

import com.application.restaurant.dao.admin.productType.IProductTypeDao;
import com.application.restaurant.dao.generic.GenericDaoCustom;
import com.application.restaurant.model.Product;
import com.application.restaurant.model.ProductType;
import com.application.restaurant.service.BaseService;
import com.application.restaurant.service.productType.IProductTypeService;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("productTypeService")
public class ProductTypeServiceImpl extends BaseService implements IProductTypeService
{
    @Setter
    @Resource(name = "productTypeDao")
    private IProductTypeDao productTypeDao;


    @Override
    protected GenericDaoCustom getDao() {
        return productTypeDao;
    }

    @Override
    @Transactional
    public ProductType save(ProductType productType) {
        return productTypeDao.saveAndFlush(productType);
    }

    @Override
    @Transactional
    public ProductType update(ProductType productType) {
        return productTypeDao.merge(productType);
    }

    @Override
    public ProductType findProductTypeById(Integer id) {
        return productTypeDao.findProductTypeById(id);
    }

    @Override
    public List<ProductType> findAllProductTypes() {
        return productTypeDao.findAll();
    }

    @Override
    @Transactional
    public void deleteProductTypeById(Integer id) {
        productTypeDao.deleteProductTypeById(id);
    }
}
