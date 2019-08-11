package com.shri.springboot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @Author: ZeeroIQ
 * @Date: 7/22/2019
 */
@Getter
@Setter
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
