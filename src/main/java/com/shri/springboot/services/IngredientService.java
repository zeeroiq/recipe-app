package com.shri.springboot.services;

import com.shri.springboot.commands.IngredientCommand;

/**
 * @Author: ZeeroIQ
 * @Date: 8/11/2019 11:01 PM
 */
public interface IngredientService {
    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
    IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);
    void deleteById(Long recipeId, Long ingredientId);
}
