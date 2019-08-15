package com.shri.springboot.services;

import com.shri.springboot.commands.RecipeCommand;
import com.shri.springboot.converters.RecipeCommandToRecipe;
import com.shri.springboot.converters.RecipeToRecipeCommand;
import com.shri.springboot.model.Recipe;
import com.shri.springboot.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @Author: ZeeroIQ
 * @Date: 7/22/2019 5:55 AM
 */
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }


    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }

    @Override
    public Recipe findById(Long l) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(l);
        if(!recipeOptional.isPresent()) {
            throw new RuntimeException("Recipe not found");
        }

        return recipeOptional.get();
    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long l) {
        return recipeToRecipeCommand.convert(findById(l));
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

        Recipe saveRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved RecipeId: "+ saveRecipe.getId());
        return recipeToRecipeCommand.convert(saveRecipe);
    }

    @Override
    public void deleteById(Long idToDelete) {
        recipeRepository.deleteById(idToDelete);
    }
}