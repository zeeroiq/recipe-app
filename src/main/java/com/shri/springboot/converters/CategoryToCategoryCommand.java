package com.shri.springboot.converters;

import com.shri.springboot.commands.CategoryCommand;
import com.shri.springboot.model.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @Author: ZeeroIQ
 * @Date: 8/11/2019 4:33 AM
 */
@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    @Synchronized
    @Nullable
    @Override
    public CategoryCommand convert(Category source) {
        if (source == null) {
            return null;
        }

        final CategoryCommand command = new CategoryCommand();
        command.setId(source.getId());
        command.setDescription(source.getDescription());
        return command;
    }
}
