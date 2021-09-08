package com.application.restaurant.controller;

import com.application.restaurant.model.Product;
import com.application.restaurant.service.product.IProductService;
import com.application.restaurant.service.product.impl.ProductServiceImpl;
import com.application.restaurant.util.UtilsForProduct;
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
@RequestMapping("/product")
public class ProductController
{
    @Autowired
    private IProductService productService;

    @GetMapping(value = "/products-in-same-category/{type_id}", headers="Accept=application/json")
    public ResponseEntity<CollectionModel<EntityModel<Product>>> getAllProductsInTheSameCategory(@PathVariable("type_id") int type_id){
        List<EntityModel<Product>> products = UtilsForProduct.getEntityModels(productService.getProductListInTheSameSubCategory(type_id));
        return ResponseEntity.ok(
                new CollectionModel<>(products,
                        linkTo(methodOn(ProductServiceImpl.class).getProductListInTheSameSubCategory(type_id)).slash("product-type").slash("products-in-same-category").slash(type_id).withSelfRel()));
    }

    @PostMapping(value = "/product", headers = "Accept=application/json")
    public ResponseEntity<Void> createNewProduct(@RequestBody Product  product, UriComponentsBuilder ucBuilder){
        productService.save(product);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/product/{product_id}").buildAndExpand(product.getProduct_id()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @GetMapping(value = "/products", headers="Accept=application/json")
    public ResponseEntity<CollectionModel<EntityModel<Product>>> listAllProductTypes() {
        List<EntityModel<Product>> products = UtilsForProduct.getEntityModels(productService.findAllProducts());
        return ResponseEntity.ok(
                new CollectionModel<>(products,
                        linkTo(methodOn(ProductServiceImpl.class).findAllProducts()).slash("product").slash("products").withSelfRel()));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProductById(@PathVariable("id") Integer id) {
        Product product = productService.findProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

/*
    @RequestMapping(value = "/newType", method = RequestMethod.GET)
    public String addNewProduct(Model model){
        Product product = new Product();
        model.addAttribute("product", product);

        return "user";
    }

    @RequestMapping(value = "/saveProductType", method = RequestMethod.POST)
    public String saveNewProduct(@ModelAttribute Product product){

        if (product.getProduct_id() == 0){
            productService.save(product);
            return "redirect:/newProduct";
        }
        else {
            productService.update(product);
            return "updateProduct";
        }
    }
    */
}
