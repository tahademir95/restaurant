package com.application.restaurant.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "system_parameters")
public class SystemParameters {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private String id;
    @Column(name = "config_key", nullable = false, unique = true)
    private String configKey;
    @Column(name = "config_value", nullable = false)
    private String configValue;
}
