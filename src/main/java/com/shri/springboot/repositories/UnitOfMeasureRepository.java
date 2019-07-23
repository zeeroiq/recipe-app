package com.shri.springboot.repositories;

import com.shri.springboot.model.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @Author: ZeeroIQ
 * @Date: 7/22/2019 4:46 AM
 */
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

    Optional<UnitOfMeasure> findByDescription(String description);
}
