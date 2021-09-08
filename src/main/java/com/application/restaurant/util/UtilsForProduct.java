package com.application.restaurant.util;

import com.application.restaurant.model.Product;
import com.application.restaurant.service.productType.impl.ProductTypeServiceImpl;
import org.springframework.hateoas.EntityModel;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class UtilsForProduct {
    public static List<EntityModel<Product>> getEntityModels(List<Product> product) {
        return StreamSupport.stream(product.spliterator(), false)
                .map(product_ -> new EntityModel<>(product_, linkTo(methodOn(ProductTypeServiceImpl.class).findProductTypeById(product_.getProduct_id()))
                        .slash("product")
                        .slash(product_.getProduct_id())
                        .withRel("products")))
                .collect(Collectors.toList());
    }
}
