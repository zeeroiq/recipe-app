package com.shri.springboot.services;

import com.shri.springboot.commands.RecipeCommand;
import com.shri.springboot.model.Recipe;

import java.util.Set;

/**
 * @Author: ZeeroIQ
 * @Date: 7/22/2019 5:52 AM
 */
public interface RecipeService{
    Set<Recipe> getRecipes();
    Recipe findById(Long l);

    RecipeCommand findCommandById(Long l);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    void deleteById(Long idToDelete);
}
