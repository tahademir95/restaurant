package com.application.restaurant.service.product;

import com.application.restaurant.model.Product;

import java.util.List;

public interface IProductService
{
    Product save(Product product);

    Product update(Product product);

    Product findProductById(Integer id);

    List<Product> findAllProducts();

    List<Product>getProductListInTheSameSubCategory(int id);
}
