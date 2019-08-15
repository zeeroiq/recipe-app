package com.shri.springboot.services;

import com.shri.springboot.commands.UnitOfMeasureCommand;

import java.util.Set;

/**
 * @Author: ZeeroIQ
 * @Date: 8/15/2019 2:55 AM
 */
public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> listAllUoms();
}
