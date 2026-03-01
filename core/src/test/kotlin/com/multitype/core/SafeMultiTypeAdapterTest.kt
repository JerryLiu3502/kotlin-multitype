package com.multitype.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SafeMultiTypeAdapterTest {
    
    @Test
    fun test_create_adapter() {
        val adapter = SafeMultiTypeAdapter()
        assertTrue(adapter.getItemCount() == 0)
    }
    
    @Test
    fun test_add_item() {
        val adapter = SafeMultiTypeAdapter()
        adapter.addItem("test")
        
        assertEquals(1, adapter.getItemCount())
    }
    
    @Test
    fun test_add_items() {
        val adapter = SafeMultiTypeAdapter()
        adapter.addItems(listOf("a", "b", "c"))
        
        assertEquals(3, adapter.getItemCount())
    }
    
    @Test
    fun test_set_items() {
        val adapter = SafeMultiTypeAdapter()
        adapter.addItem("initial")
        adapter.setItems(listOf("a", "b"))
        
        assertEquals(2, adapter.getItemCount())
        assertEquals("a", adapter.getItem(0))
    }
    
    @Test
    fun test_builder() {
        val adapter = SafeMultiTypeAdapterBuilder.create {
            addItem("test")
            addItems(listOf("a", "b"))
        }
        
        assertEquals(3, adapter.getItemCount())
    }
    
    @Test
    fun test_safe_adapter_builder() {
        val adapter = safeMultiTypeAdapter {
            addItem("item1")
            addItem("item2")
        }
        
        assertEquals(2, adapter.getItemCount())
    }
    
    @Test
    fun test_get_adapter() {
        val adapter = SafeMultiTypeAdapter()
        adapter.addItem("test")
        
        val underlying = adapter.getAdapter()
        assertTrue(underlying is MultiTypeRecyclerViewAdapter)
    }
}
