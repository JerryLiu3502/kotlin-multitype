package com.multitype.core

import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class EmptyViewHandlerTest {
    
    @Test
    fun test_show_empty_view() {
        val handler = EmptyViewHandlerBuilder.create()
        
        handler.showEmptyView()
        
        assertTrue(handler.isShowingEmptyView())
        assertFalse(handler.isShowingContentView())
    }
    
    @Test
    fun test_show_content_view() {
        val handler = EmptyViewHandlerBuilder.create()
        
        handler.showContentView()
        
        assertFalse(handler.isShowingEmptyView())
        assertTrue(handler.isShowingContentView())
    }
    
    @Test
    fun test_update_with_empty_items() {
        val handler = EmptyViewHandlerBuilder.create()
        
        handler.update(0)
        
        assertTrue(handler.isShowingEmptyView())
    }
    
    @Test
    fun test_update_with_items() {
        val handler = EmptyViewHandlerBuilder.create()
        
        handler.update(5)
        
        assertTrue(handler.isShowingContentView())
    }
    
    @Test
    fun test_default_state() {
        val handler = EmptyViewHandlerBuilder.create()
        
        // Default is showing content (empty = false)
        assertFalse(handler.isShowingEmptyView())
        assertTrue(handler.isShowingContentView())
    }
}
