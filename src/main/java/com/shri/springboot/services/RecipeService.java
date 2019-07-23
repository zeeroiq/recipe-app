package com.shri.springboot.services;

import com.shri.springboot.model.Recipe;

import java.util.Set;

/**
 * @Author: ZeeroIQ
 * @Date: 7/22/2019 5:52 AM
 */
public interface RecipeService{
    Set<Recipe> getRecipies();
}
