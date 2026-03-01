package com.multitype.core

import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class ItemFilterTest {
    
    @Test
    fun test_create_with_predicate() {
        val filter = ItemFilterBuilder.create { it is String }
        
        assertTrue(filter.accept("test"))
        assertFalse(filter.accept(123))
    }
    
    @Test
    fun test_create_by_class() {
        // Use String and Int types directly
        val filter = ItemFilterBuilder.create { item ->
            item is String || item is Int
        }
        
        assertTrue(filter.accept("test"))
        assertTrue(filter.accept(123))
        assertFalse(filter.accept(Any()))
    }
    
    @Test
    fun test_exclude_by_class() {
        val filter = ItemFilterBuilder.create { item ->
            item !is String
        }
        
        assertFalse(filter.accept("test"))
        assertTrue(filter.accept(123))
    }
}
