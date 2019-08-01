package com.shri.springboot.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @Author: ZeeroIQ
 * @Date: 7/22/2019
 */
@Data
@EqualsAndHashCode(exclude = "{recipe}")
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Recipe recipe;

    @Lob
    private String recipeNotes;
}
