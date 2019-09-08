package com.shri.springboot.services;

import com.shri.springboot.commands.RecipeCommand;
import com.shri.springboot.converters.RecipeCommandToRecipe;
import com.shri.springboot.converters.RecipeToRecipeCommand;
import com.shri.springboot.exceptions.NotFoundException;
import com.shri.springboot.model.Recipe;
import com.shri.springboot.repositories.RecipeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

/**
 * @Author: ZeeroIQ
 * @Date: 7/26/2019 5:02 AM
 */
public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;
    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;
    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeRepository recipeRepository;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void getRecipeByIdNotFoundTest() throws Exception{
        Optional<Recipe> optionalRecipe = Optional.empty();

        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);

//        Recipe recipeReturned = recipeService.findById(1L);
        Assertions.assertThrows(NotFoundException.class, () -> recipeService.findById(1L));
    }


    @Test
    public void getRecipeCommandById() throws Exception{
        Recipe recipe = new Recipe();
        recipe.setId(1l);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(1l);

        when(recipeToRecipeCommand.convert(any())).thenReturn(recipeCommand);


        RecipeCommand commandById = recipeService.findCommandById(1L);

        assertNotNull("null recipe returned", commandById);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    public void getRecipeById() throws Exception{
        Recipe recipe = new Recipe();
        recipe.setId(1l);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipeReturned = recipeService.findById(1L);

        assertNotNull("null recipe returned", recipeReturned);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    void getRecipes() throws Exception{
        Recipe recipe = new Recipe();
        HashSet recipeData = new HashSet();
        recipeData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipeData);
        Set<Recipe> recipes = recipeService.getRecipes();
        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
        verify(recipeRepository, never()).findById(anyLong());

    }

    @Test
    public void testDeleteById() throws Exception {
        Long idToDelete = Long.valueOf(2L);
        recipeService.deleteById(idToDelete);

        verify(recipeRepository, times(1)).deleteById(anyLong());
    }
}