package com.shri.springboot.services;

import com.shri.springboot.model.Recipe;
import com.shri.springboot.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author: ZeeroIQ
 * @Date: 8/16/2019 1:28 AM
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }


    @Override
    public void saveImageFile(Long id, MultipartFile imageFile){
        try {
            Recipe recipe = recipeRepository.findById(id).get();
            Byte[] byteObject = new Byte[imageFile.getBytes().length];

            int i=0;
            for (byte b: imageFile.getBytes()) {
                byteObject[i++] = b;
            }

            recipe.setImage(byteObject);

            recipeRepository.save(recipe);
        } catch (IOException e) {
            log.info("Error occuered");
            e.printStackTrace();
        }

    }
}
