package com.multitype.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ViewTypeProviderTest {
    
    @Test
    fun test_create_by_class() {
        val provider = ViewTypeProviderBuilder.createByClass()
        
        val type1 = provider.getViewType("string")
        val type2 = provider.getViewType(123)
        
        assertTrue(type1 != type2)
    }
    
    @Test
    fun test_create_custom() {
        var called = false
        
        val provider = ViewTypeProviderBuilder.create { item ->
            called = true
            1
        }
        
        provider.getViewType("test")
        
        assertTrue(called)
    }
    
    @Test
    fun test_same_class_same_type() {
        val provider = ViewTypeProviderBuilder.createByClass()
        
        val type1 = provider.getViewType("a")
        val type2 = provider.getViewType("b")
        
        assertEquals(type1, type2)
    }
}
