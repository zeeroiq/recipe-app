package com.shri.springboot.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author: ZeeroIQ
 * @Date: 8/11/2019 4:23 AM
 */
@Getter
@Setter
@NoArgsConstructor
public class CategoryCommand {

    private Long id;
    private String description;
}
