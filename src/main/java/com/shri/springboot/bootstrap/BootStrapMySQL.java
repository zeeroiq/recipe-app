package com.shri.springboot.bootstrap;

import com.shri.springboot.model.Category;
import com.shri.springboot.model.UnitOfMeasure;
import com.shri.springboot.repositories.CategoryRepository;
import com.shri.springboot.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * @Author: ZeeroIQ
 * @Date: 7/22/2019 5:37 AM
 */
@Slf4j
@Component
@Profile({"dev", "prod"})
public class BootStrapMySQL implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public BootStrapMySQL(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(categoryRepository.count() == 0L) {
            log.debug("Loading Categories");
            loadCategories();
        }

        if(unitOfMeasureRepository.count() == 0L) {
            log.debug("Loading Unit Of Measures");
            loadUom();
        }
    }

    private void loadCategories() {
        Category category1 = new Category();
        category1.setDescription("Indian");
        categoryRepository.save(category1);

        Category category2 = new Category();
        category2.setDescription("American");
        categoryRepository.save(category2);

        Category category3 = new Category();
        category3.setDescription("Canadian");
        categoryRepository.save(category3);

        Category category4 = new Category();
        category4.setDescription("Mexican");
        categoryRepository.save(category4);

    }

    private void loadUom() {

        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setDescription("Teaspoon");
        unitOfMeasureRepository.save(uom1);

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setDescription("TableSpoon");
        unitOfMeasureRepository.save(uom2);

        UnitOfMeasure uom3 = new UnitOfMeasure();
        uom3.setDescription("Cup");
        unitOfMeasureRepository.save(uom3);

        UnitOfMeasure uom4 = new UnitOfMeasure();
        uom4.setDescription("Ounce");
        unitOfMeasureRepository.save(uom4);

    }

}
