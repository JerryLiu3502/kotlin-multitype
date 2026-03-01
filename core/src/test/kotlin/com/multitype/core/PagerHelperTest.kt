package com.multitype.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class PagerHelperTest {
    
    @Test
    fun test_create_helper() {
        val helper = PagerHelperBuilder.create()
        assertEquals(0, helper.getCount())
    }
    
    @Test
    fun test_create_with_items() {
        val items = listOf("a", "b", "c")
        val helper = PagerHelperBuilder.create(items)
        
        assertEquals(3, helper.getCount())
        assertEquals("a", helper.getItem(0))
    }
    
    @Test
    fun test_add_item() {
        val helper = PagerHelperBuilder.create()
        helper.addItem("test")
        
        assertEquals(1, helper.getCount())
        assertEquals("test", helper.getItem(0))
    }
    
    @Test
    fun test_remove_item() {
        val helper = PagerHelperBuilder.create()
        helper.addItem("test")
        
        val removed = helper.removeItem("test")
        
        assertTrue(removed)
        assertEquals(0, helper.getCount())
    }
    
    @Test
    fun test_clear() {
        val helper = PagerHelperBuilder.create()
        helper.addItem("a")
        helper.addItem("b")
        
        helper.clear()
        
        assertEquals(0, helper.getCount())
        assertEquals(0, helper.getCurrentPosition())
    }
    
    @Test
    fun test_current_position() {
        val helper = PagerHelperBuilder.create()
        
        assertEquals(0, helper.getCurrentPosition())
        
        helper.setCurrentPosition(5)
        
        assertEquals(5, helper.getCurrentPosition())
    }
    
    @Test
    fun test_get_items() {
        val helper = PagerHelperBuilder.create()
        helper.addItem("a")
        helper.addItem("b")
        
        val items = helper.getItems()
        
        assertEquals(2, items.size)
        assertTrue(items.contains("a"))
        assertTrue(items.contains("b"))
    }
    
    @Test
    fun test_set_items() {
        val helper = PagerHelperBuilder.create()
        helper.setItems(listOf("x", "y"))
        
        assertEquals(2, helper.getCount())
        assertEquals("x", helper.getItem(0))
    }
}
