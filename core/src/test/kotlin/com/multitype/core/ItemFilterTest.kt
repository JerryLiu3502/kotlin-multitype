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
        val filter = ItemFilterBuilder.createByClass(String::class.java, Int::class.java)
        
        assertTrue(filter.accept("test"))
        assertTrue(filter.accept(123))
        assertFalse(filter.accept(Any()))
    }
    
    @Test
    fun test_exclude_by_class() {
        val filter = ItemFilterBuilder.excludeByClass(String::class.java)
        
        assertFalse(filter.accept("test"))
        assertTrue(filter.accept(123))
    }
}
