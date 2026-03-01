package com.multitype.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ItemEventDispatcherTest {
    
    @Test
    fun test_create_dispatcher() {
        val dispatcher = ItemEventDispatcher()
        assertTrue(dispatcher != null)
    }
    
    @Test
    fun test_add_click_listener() {
        val dispatcher = ItemEventDispatcher()
        var clicked = false
        
        dispatcher.addClickListener(0) { _, _ ->
            clicked = true
        }
        
        dispatcher.dispatchClick("item", 0, 0)
        assertTrue(clicked)
    }
    
    @Test
    fun test_add_long_click_listener() {
        val dispatcher = ItemEventDispatcher()
        var longClicked = false
        
        dispatcher.addLongClickListener(0) { _, _ ->
            longClicked = true
        }
        
        dispatcher.dispatchLongClick("item", 0, 0)
        assertTrue(longClicked)
    }
    
    @Test
    fun test_global_click_listener() {
        val dispatcher = ItemEventDispatcher()
        var clicked = false
        
        dispatcher.addGlobalClickListener { _, _ ->
            clicked = true
        }
        
        dispatcher.dispatchClick("item", 0, 0)
        dispatcher.dispatchClick("item", 1, 1)
        assertTrue(clicked)
    }
    
    @Test
    fun test_different_view_types() {
        val dispatcher = ItemEventDispatcher()
        var clickType0 = false
        var clickType1 = false
        
        dispatcher.addClickListener(0) { _, _ ->
            clickType0 = true
        }
        dispatcher.addClickListener(1) { _, _ ->
            clickType1 = true
        }
        
        dispatcher.dispatchClick("item", 0, 0)
        assertTrue(clickType0)
        assertTrue(!clickType1)
        
        dispatcher.dispatchClick("item", 1, 1)
        assertTrue(clickType1)
    }
    
    @Test
    fun test_clear() {
        val dispatcher = ItemEventDispatcher()
        var clicked = false
        
        dispatcher.addClickListener(0) { _, _ ->
            clicked = true
        }
        
        dispatcher.clear()
        dispatcher.dispatchClick("item", 0, 0)
        assertTrue(!clicked)
    }
    
    @Test
    fun test_builder() {
        var clicked = false
        
        val dispatcher = ItemEventDispatcherBuilder.create { _, _ ->
            clicked = true
        }
        
        dispatcher.dispatchClick("item", 0, 0)
        assertTrue(clicked)
    }
    
    @Test
    fun test_builder_with_both() {
        var clicked = false
        var longClicked = false
        
        val dispatcher = ItemEventDispatcherBuilder.create(
            onClick = { _, _ -> clicked = true },
            onLongClick = { _, _ -> longClicked = true }
        )
        
        dispatcher.dispatchClick("item", 0, 0)
        dispatcher.dispatchLongClick("item", 0, 0)
        
        assertTrue(clicked)
        assertTrue(longClicked)
    }
}
