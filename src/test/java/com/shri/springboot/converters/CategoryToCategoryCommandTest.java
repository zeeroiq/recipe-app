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
class CategoryToCategoryCommandTest {

    public static final Long LONG_ID = 1l;
    public  static final String DESCRIPTION = "description";

    private CategoryToCategoryCommand converter;

    @BeforeEach
    void setUp() {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    void convert() {

        Category category = new Category();
        category.setId(LONG_ID);
        category.setDescription(DESCRIPTION);

        CategoryCommand command = converter.convert(category);

        assertNotNull(category);
        assertEquals(LONG_ID, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }
}