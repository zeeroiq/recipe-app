package com.shri.springboot.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Author: ZeeroIQ
 * @Date: 7/26/2019 4:38 AM
 */
public class CategoryTest {

    Category category;

    @BeforeEach
    public void setUp() {
        category = new Category();
    }

    @Test
    void getId() throws Exception{
        Long id = 4L;
        category.setId(id);
        assertEquals(id, category.getId());
    }

    @Test
    void getDescription() {
    }

    @Test
    void getRecipes() {
    }
}