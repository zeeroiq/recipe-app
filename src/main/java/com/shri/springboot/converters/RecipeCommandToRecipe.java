package com.shri.springboot.converters;

import com.shri.springboot.commands.RecipeCommand;
import com.shri.springboot.model.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;


/**
 * @Author: ZeeroIQ
 * @Date: 8/11/2019 4:34 AM
 */
@Component
public class RecipeCommandToRecipe implements Converter <RecipeCommand, Recipe>{

    private final CategoryCommandToCategory categoryConverter;
    private final IngredientCommandToIngredient ingredientConverter;
    private final NotesCommandToNotes notesConverter;

    public RecipeCommandToRecipe(CategoryCommandToCategory categoryConverter, IngredientCommandToIngredient ingredientConverter, NotesCommandToNotes notesConverter) {
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand src) {

        if(src == null) {
            return null;
        }

        final Recipe recipe = new Recipe();
        recipe.setId(src.getId());
        recipe.setCookTime(src.getCookTime());
        recipe.setPrepTime(src.getPrepTime());
        recipe.setDescription(src.getDescription());
        recipe.setDifficulty(src.getDifficulty());
        recipe.setDirections(src.getDirections());
        recipe.setServings(src.getServings());
        recipe.setSource(src.getSource());
        recipe.setUrl(src.getUrl());
        recipe.setNotes(notesConverter.convert(src.getNotes()));

        if(src.getCategories() != null && src.getCategories().size() > 0) {
            src.getCategories().forEach(category -> recipe.getCategories().add(categoryConverter.convert(category)));
        }

        if(src.getIngredients() != null && src.getIngredients().size() > 0) {
            src.getIngredients()
                    .forEach(ingredient -> recipe.getIngredients().add(ingredientConverter.convert(ingredient)));
        }

        return recipe;
    }
}
