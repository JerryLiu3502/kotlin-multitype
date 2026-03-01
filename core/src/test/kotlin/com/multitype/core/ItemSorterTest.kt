package com.multitype.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ItemSorterTest {
    
    @Test
    fun test_create_with_comparator() {
        val sorter = ItemSorterBuilder.create { item1, item2 ->
            (item1 as Int) - (item2 as Int)
        }
        
        assertTrue(sorter.compare(2, 1) > 0)
        assertTrue(sorter.compare(1, 2) < 0)
        assertEquals(0, sorter.compare(1, 1))
    }
    
    @Test
    fun test_ascending_sorter() {
        val sorter = ItemSorterBuilder.createAscending()
        
        assertTrue(sorter.compare(1, 2) < 0)
        assertTrue(sorter.compare(2, 1) > 0)
        assertEquals(0, sorter.compare(1, 1))
    }
    
    @Test
    fun test_descending_sorter() {
        val sorter = ItemSorterBuilder.createDescending()
        
        assertTrue(sorter.compare(1, 2) > 0)
        assertTrue(sorter.compare(2, 1) < 0)
        assertEquals(0, sorter.compare(1, 1))
    }
    
    @Test
    fun test_string_sorter() {
        val sorter = ItemSorterBuilder.createAscending()
        
        assertTrue(sorter.compare("apple", "banana") < 0)
        assertTrue(sorter.compare("banana", "apple") > 0)
    }
}
