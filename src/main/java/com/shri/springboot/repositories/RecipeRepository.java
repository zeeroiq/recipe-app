package com.shri.springboot.repositories;

import com.shri.springboot.model.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author: ZeeroIQ
 * @Date: 7/22/2019 4:44 AM
 */
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
