package com.application.restaurant.dao.admin.product;

import com.application.restaurant.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("productDao")
public interface IProductDao extends JpaRepository<Product, String>, ProductDaoCustom
{
    @Query("select product from Product product where product.product_id = :product_id")
    Product findProductById(@Param("product_id") Integer product_id);

    @Query("select product from Product product where product.productType.id = :type_id")
    List<Product> getProductListInTheSameSubCategory(int type_id);

    List<Product> findAll();

}
