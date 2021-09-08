package com.application.restaurant.service.productType;



import com.application.restaurant.model.ProductType;

import java.util.List;

public interface IProductTypeService
{
    ProductType save(ProductType productType);

    ProductType update(ProductType productType);

    ProductType findProductTypeById(Integer id);

    List<ProductType> findAllProductTypes();

    void deleteProductTypeById(Integer id);
}
