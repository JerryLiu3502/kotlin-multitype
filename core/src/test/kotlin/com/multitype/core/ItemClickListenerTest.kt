package com.multitype.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class ItemClickListenerTest {
    
    @Test
    fun test_click_listener() {
        var clickedPosition = -1
        var clickedItem: Any? = null
        
        val listener = ItemClickListenerBuilder.create { position, item ->
            clickedPosition = position
            clickedItem = item
        }
        
        listener.onItemClick(5, "test item")
        
        assertEquals(5, clickedPosition)
        assertEquals("test item", clickedItem)
    }
    
    @Test
    fun test_long_click_listener() {
        var longClicked = false
        
        val listener = ItemClickListenerBuilder.create(
            onClick = { _, _ -> },
            onLongClick = { _, _ -> true }
        )
        
        val result = listener.onItemLongClick(0, "item")
        
        assertTrue(result)
    }
    
    @Test
    fun test_simple_listener() {
        var called = false
        
        val listener = object : ItemClickListener {
            override fun onItemClick(position: Int, item: Any) {
                called = true
            }
        }
        
        listener.onItemClick(0, "test")
        
        assertTrue(called)
    }
    
    @Test
    fun test_default_long_click_returns_false() {
        val listener = object : ItemClickListener {
            override fun onItemClick(position: Int, item: Any) {}
        }
        
        assertFalse(listener.onItemLongClick(0, "test"))
    }
}
