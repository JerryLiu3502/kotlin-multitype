package com.multitype.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class MultiTypeExtTest {
    
    @Test
    fun test_is_empty() {
        val adapter = MultiTypeRecyclerViewAdapter()
        assertTrue(adapter.isEmpty())
    }
    
    @Test
    fun test_is_not_empty() {
        val adapter = MultiTypeRecyclerViewAdapter()
        adapter.addItem("test")
        assertTrue(adapter.isNotEmpty())
    }
    
    @Test
    fun test_contains() {
        val adapter = MultiTypeRecyclerViewAdapter()
        adapter.addItem("test")
        
        assertTrue(adapter.contains("test"))
        assertFalse(adapter.contains("not exists"))
    }
    
    @Test
    fun test_index_of() {
        val adapter = MultiTypeRecyclerViewAdapter()
        adapter.addItems(listOf("a", "b", "c"))
        
        assertEquals(0, adapter.indexOf("a"))
        assertEquals(1, adapter.indexOf("b"))
        assertEquals(2, adapter.indexOf("c"))
        assertEquals(-1, adapter.indexOf("not exists"))
    }
    
    @Test
    fun test_get_item_at() {
        val adapter = MultiTypeRecyclerViewAdapter()
        adapter.addItems(listOf("a", "b", "c"))
        
        assertEquals("a", adapter.getItemAt<String>(0))
        assertEquals("b", adapter.getItemAt<String>(1))
    }
    
    @Test
    fun test_get_items_of_type() {
        val adapter = MultiTypeRecyclerViewAdapter()
        adapter.addItems(listOf("a", 1, "b", 2, "c"))
        
        val strings = adapter.getItemsOfType(String::class.java)
        assertEquals(3, strings.size)
    }
}
