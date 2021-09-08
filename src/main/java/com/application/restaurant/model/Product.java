package com.application.restaurant.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT")
@Data
@ToString(callSuper = true, of = "")
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer product_id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "id")
    private ProductType productType;

}
