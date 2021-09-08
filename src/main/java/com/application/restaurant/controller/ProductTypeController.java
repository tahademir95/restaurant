package com.application.restaurant.controller;

import com.application.restaurant.model.ProductType;
import com.application.restaurant.service.productType.IProductTypeService;
import com.application.restaurant.service.productType.impl.ProductTypeServiceImpl;
import com.application.restaurant.util.UtilsForProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/product-type")
public class ProductTypeController
{
    @Autowired
    private IProductTypeService productTypeService;

    @GetMapping(value = "/product-types", headers="Accept=application/json")
    public ResponseEntity<CollectionModel<EntityModel<ProductType>>> listAllProductTypes() {
        List<EntityModel<ProductType>> productType = UtilsForProductType.getEntityModels(productTypeService.findAllProductTypes());
        return ResponseEntity.ok(
                new CollectionModel<>(productType,
                        linkTo(methodOn(ProductTypeServiceImpl.class).findAllProductTypes()).slash("product-type").slash("product-types").withSelfRel()));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductType> getProductById(@PathVariable("id") Integer id) {
        ProductType productType = productTypeService.findProductTypeById(id);
        return new ResponseEntity<>(productType, HttpStatus.OK);
    }

    @PostMapping(value = "/product-type", headers = "Accept=application/json")
    public ResponseEntity<Void> createNewProductType(@RequestBody ProductType  productType, UriComponentsBuilder ucBuilder) {
        productTypeService.save(productType);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/product-type/{product_id}").buildAndExpand(productType.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductType> updateProductById(@PathVariable("id") Integer id, @RequestBody ProductType productTypeName) {
        ProductType productType = productTypeService.findProductTypeById(id);
        productType.setType_name(productTypeName.getType_name());
        productTypeService.update(productType);
        return new ResponseEntity<>(productType, HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}", headers ="Accept=application/json")
    public ResponseEntity<ProductType> deleteProduct(@PathVariable("id") int id){
        ProductType productType = productTypeService.findProductTypeById(id);
        if (productType == null) {
            return new ResponseEntity<ProductType>(HttpStatus.NOT_FOUND);
        }
        productTypeService.deleteProductTypeById(id);
        return new ResponseEntity<ProductType>(HttpStatus.NO_CONTENT);
    }
}
