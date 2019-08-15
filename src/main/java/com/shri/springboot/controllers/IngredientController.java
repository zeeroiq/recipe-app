package com.shri.springboot.controllers;

import com.shri.springboot.commands.IngredientCommand;
import com.shri.springboot.commands.RecipeCommand;
import com.shri.springboot.commands.UnitOfMeasureCommand;
import com.shri.springboot.services.IngredientService;
import com.shri.springboot.services.RecipeService;
import com.shri.springboot.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: ZeeroIQ
 * @Date: 8/11/2019 9:34 PM
 */
@Slf4j
@Controller
public class IngredientController {

    private final IngredientService ingredientService;
    private final RecipeService recipeService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(IngredientService ingredientService, RecipeService recipeService, UnitOfMeasureService unitOfMeasureService) {
        this.ingredientService = ingredientService;
        this.recipeService = recipeService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable("recipeId") String recipeId, Model model) {

        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));

        return "recipe/ingredient/list";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showRecipeIngredient(@PathVariable("recipeId") String recipeId,
                                       @PathVariable("ingredientId") String ingredientId, Model model) {

        model.addAttribute("ingredient",
                ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));

        return "recipe/ingredient/show";
    }

    @GetMapping
	@RequestMapping("/recipe/{recipeId}/ingredient/new")
    public String newRecipe(@PathVariable("recipeId") String recipeId, Model model){

        //make sure we have a good id value
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));
        //todo raise exception if null

        //need to return back parent id for hidden form property
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));
        model.addAttribute("ingredient", ingredientCommand);

        //init uom
        ingredientCommand.setUnitOfMeasure(new UnitOfMeasureCommand());

        model.addAttribute("uomList",  unitOfMeasureService.listAllUoms());

        return "recipe/ingredient/ingredientForm";
    }
	@GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{id}/update")
    public String updateRecipeIngredient(@PathVariable("recipeId") String recipeId,
                                       @PathVariable("id") String id, Model model) {

        model.addAttribute("ingredient",
                ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));
        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());

        return "recipe/ingredient/ingredientForm";
    }

    @PostMapping
    @RequestMapping("/recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command) {
        IngredientCommand ingredientCommand = ingredientService.saveIngredientCommand(command);

        log.debug("saved recipe id: {}", ingredientCommand.getRecipeId());
        log.debug("saved ingredient id: {}", ingredientCommand.getId());

        return "redirect:/recipe/" + ingredientCommand.getRecipeId() + "/ingredient/" + ingredientCommand.getId() + "/show";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/delete")
    public String deleteIngredient(@PathVariable("recipeId") String recipeId, @PathVariable("ingredientId") String ingredientId) {
        log.info("Deleting ingredient id: {}", ingredientId);
        ingredientService.deleteById(Long.valueOf(recipeId), Long.valueOf(ingredientId));

        return "redirect:/recipe/" + recipeId + "/ingredients";

    }
}
