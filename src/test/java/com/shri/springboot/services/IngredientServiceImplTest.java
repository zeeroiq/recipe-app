package com.shri.springboot.services;

import com.shri.springboot.commands.IngredientCommand;
import com.shri.springboot.converters.IngredientCommandToIngredient;
import com.shri.springboot.converters.IngredientToIngredientCommand;
import com.shri.springboot.converters.UnitOfMeasureCommandToUnitOfMeasure;
import com.shri.springboot.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.shri.springboot.model.Ingredient;
import com.shri.springboot.model.Recipe;
import com.shri.springboot.repositories.RecipeRepository;
import com.shri.springboot.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * @Author: ZeeroIQ
 * @Date: 8/11/2019 11:21 PM
 */
class IngredientServiceImplTest {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    @Mock
    RecipeRepository recipeRepository;
    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;


    IngredientService ingredientService;

    public IngredientServiceImplTest() {
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        this.ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand, ingredientCommandToIngredient, recipeRepository, unitOfMeasureRepository);
    }

    @Test
    void findByRecipeIdAndIngredientIdTest() throws Exception{
        Recipe recipe = new Recipe();
        recipe.setId(1l);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1l);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2l);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3l);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);

        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1l, 2l);

        assertEquals(Long.valueOf(1l), ingredientCommand.getRecipeId());
        assertEquals(Long.valueOf(2l), ingredientCommand.getId());
        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test
    public void saveIngredientCommandTest() {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(3L);
        ingredientCommand.setRecipeId(2l);

        Optional<Recipe> optionalRecipe = Optional.of(new Recipe());

        Recipe savedRecipe = new Recipe();
        savedRecipe.addIngredient(new Ingredient());
        savedRecipe.getIngredients().iterator().next().setId(3L);

        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);
        when(recipeRepository.save(any())).thenReturn(savedRecipe);

        IngredientCommand saveCommand = ingredientService.saveIngredientCommand(ingredientCommand);

        assertEquals(Long.valueOf(3L), saveCommand.getId());
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(Recipe.class));


    }

    @Test
    public void deleteByIdTest() throws Exception {
        Recipe recipe = new Recipe();
        Ingredient ingredient = new Ingredient();

        ingredient.setId(3l);
        recipe.addIngredient(ingredient);
        ingredient.setRecipe(recipe);

        Optional<Recipe> optionalRecipe = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);

        ingredientService.deleteById(1l, 3l);

        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, times(1)).save(any(Recipe.class));
    }
}