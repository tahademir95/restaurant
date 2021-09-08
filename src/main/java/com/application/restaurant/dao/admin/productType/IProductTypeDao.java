package com.application.restaurant.dao.admin.productType;


import com.application.restaurant.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("productTypeDao")
public interface IProductTypeDao extends JpaRepository<ProductType, String>, ProductTypeDaoCustom
{
    @Query("select type from ProductType type where type.id = :id")
    ProductType findProductTypeById(@Param("id") Integer id);

    List<ProductType> findAll();

    @Modifying
    @Query("delete from ProductType type where type.id = :id")
    void deleteProductTypeById(@Param("id") Integer id);

}
