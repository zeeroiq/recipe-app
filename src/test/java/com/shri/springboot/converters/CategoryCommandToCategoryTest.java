package com.shri.springboot.converters;

import com.shri.springboot.commands.CategoryCommand;
import com.shri.springboot.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: ZeeroIQ
 * @Date: 8/11/2019 1:40 PM
 */
class CategoryCommandToCategoryTest {

    public static final Long LONG_ID = 1l;
    public  static final String DESCRIPTION = "description";

    private CategoryCommandToCategory converter;

    @BeforeEach
    void setUp() {
        converter = new CategoryCommandToCategory();
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    void convert() {

        CategoryCommand command = new CategoryCommand();
        command.setId(LONG_ID);
        command.setDescription(DESCRIPTION);

        Category category = converter.convert(command);

        assertNotNull(category);
        assertEquals(LONG_ID, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }
}