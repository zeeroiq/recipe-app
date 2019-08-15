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
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure src) {
        if (src != null) {
			final UnitOfMeasureCommand command = new UnitOfMeasureCommand();
	        command.setId(src.getId());
	        command.setDescription(src.getDescription());

	        return command;
        }

        
            return null;
    }
}
