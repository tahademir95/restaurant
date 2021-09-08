package com.application.restaurant.util;

import com.application.restaurant.model.ProductType;
import com.application.restaurant.service.productType.impl.ProductTypeServiceImpl;
import org.springframework.hateoas.EntityModel;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class UtilsForProductType {
    public static List<EntityModel<ProductType>> getEntityModels(List<ProductType> productTypes) {
        return StreamSupport.stream(productTypes.spliterator(), false)
                .map(productType -> new EntityModel<>(productType, linkTo(methodOn(ProductTypeServiceImpl.class).findProductTypeById(productType.getId()))
                        .slash("product-type")
                        .slash(productType.getId())
                        .withRel("productTypes")))
                .collect(Collectors.toList());
    }
}
