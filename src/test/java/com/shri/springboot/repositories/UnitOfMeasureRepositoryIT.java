package com.shri.springboot.repositories;

import com.shri.springboot.model.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Author: ZeeroIQ
 * @Date: 8/2/2019 5:23 AM
 */

@DataJpaTest
class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository uomRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    public void findByDescription() throws Exception{
        Optional<UnitOfMeasure> uomOptional = uomRepository.findByDescription("Teaspoon");
        assertEquals("Teaspoon", uomOptional.get().getDescription());
    }
}