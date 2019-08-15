package com.shri.springboot.services;

import com.shri.springboot.commands.UnitOfMeasureCommand;
import com.shri.springboot.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.shri.springboot.model.UnitOfMeasure;
import com.shri.springboot.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @Author: ZeeroIQ
 * @Date: 8/15/2019 3:01 AM
 */
class UnitOfMeasureServiceImplTest {

    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand =
            new UnitOfMeasureToUnitOfMeasureCommand();
    UnitOfMeasureService uomService;

    @Mock
    UnitOfMeasureRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        uomService = new UnitOfMeasureServiceImpl(repository, unitOfMeasureToUnitOfMeasureCommand);
    }

    @Test
    void listAllUoms() {
        Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(1L);
        unitOfMeasures.add(unitOfMeasure);

        UnitOfMeasure uom = new UnitOfMeasure();
        unitOfMeasure.setId(2l);
        unitOfMeasures.add(uom);

        when(repository.findAll()).thenReturn(unitOfMeasures);

        Set<UnitOfMeasureCommand> commands = uomService.listAllUoms();

        assertEquals(2, commands.size());
        verify(repository, times(1)).findAll();
    }
}