package com.multitype.core

import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.assertFalse
import kotlin.test.assertEquals

class ItemSelectorTest {
    
    @Test
    fun test_select() {
        val selector = ItemSelectorBuilder.create()
        
        selector.select("item")
        
        assertTrue(selector.isSelected("item"))
    }
    
    @Test
    fun test_deselect() {
        val selector = ItemSelectorBuilder.create()
        selector.select("item")
        
        selector.deselect("item")
        
        assertFalse(selector.isSelected("item"))
    }
    
    @Test
    fun test_toggle() {
        val selector = ItemSelectorBuilder.create()
        
        val result = selector.toggle("item")
        
        assertTrue(result)
        assertTrue(selector.isSelected("item"))
        
        val result2 = selector.toggle("item")
        
        assertFalse(result2)
        assertFalse(selector.isSelected("item"))
    }
    
    @Test
    fun test_clear() {
        val selector = ItemSelectorBuilder.create()
        selector.select("a")
        selector.select("b")
        
        selector.clear()
        
        assertEquals(0, selector.getSelectedCount())
    }
    
    @Test
    fun test_select_all() {
        val selector = ItemSelectorBuilder.create()
        
        selector.selectAll(listOf("a", "b", "c"))
        
        assertEquals(3, selector.getSelectedCount())
    }
    
    @Test
    fun test_has_selection() {
        val selector = ItemSelectorBuilder.create()
        
        assertFalse(selector.hasSelection())
        
        selector.select("item")
        
        assertTrue(selector.hasSelection())
    }
}
