package com.shri.springboot.services;

import com.shri.springboot.model.Recipe;
import com.shri.springboot.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @Author: ZeeroIQ
 * @Date: 7/26/2019 5:02 AM
 */
public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;
    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    void getRecipies() throws Exception{
        Recipe recipe = new Recipe();
        HashSet recipeData = new HashSet();
        recipeData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipeData);
        Set<Recipe> recipes = recipeService.getRecipies();
        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();

    }
}