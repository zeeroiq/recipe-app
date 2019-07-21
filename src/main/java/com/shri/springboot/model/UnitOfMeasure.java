package com.shri.springboot.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author: ZeeroIQ
 * @Date: 7/22/2019
 */
@Entity
@Data
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
}
