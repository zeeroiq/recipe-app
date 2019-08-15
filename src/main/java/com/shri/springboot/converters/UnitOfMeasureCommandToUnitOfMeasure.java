package com.shri.springboot.converters;

import com.shri.springboot.commands.UnitOfMeasureCommand;
import com.shri.springboot.model.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @Author: ZeeroIQ
 * @Date: 8/11/2019 4:35 AM
 */
@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand src) {
        if (src == null) {
            return null;
        }

        final UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(src.getId());
        unitOfMeasure.setDescription(src.getDescription());

        return unitOfMeasure;
    }
}
