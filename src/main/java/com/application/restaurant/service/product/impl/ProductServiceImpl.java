package com.application.restaurant.service.product.impl;

import com.application.restaurant.dao.admin.product.IProductDao;
import com.application.restaurant.dao.generic.GenericDaoCustom;
import com.application.restaurant.model.Product;
import com.application.restaurant.service.BaseService;
import com.application.restaurant.service.product.IProductService;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("productService")
public class ProductServiceImpl extends BaseService implements IProductService
{
    @Setter
    @Resource(name = "productDao")
    private IProductDao productDao;

    @Override
    protected GenericDaoCustom getDao() {
        return productDao;
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return productDao.saveAndFlush(product);
    }

    @Override
    @Transactional
    public Product update(Product product) {
        return productDao.merge(product);
    }

    @Override
    public Product findProductById(Integer id) {
        return productDao.findProductById(id);
    }

    @Override
    public List<Product> findAllProducts() {
        return productDao.findAll();
    }

    @Override
    public List<Product> getProductListInTheSameSubCategory(int id) {
        return productDao.getProductListInTheSameSubCategory(id);
    }
}
