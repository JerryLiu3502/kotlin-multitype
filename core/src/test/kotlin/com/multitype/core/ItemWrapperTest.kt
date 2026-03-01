package com.multitype.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class ItemWrapperTest {
    
    @Test
    fun test_create_wrapper() {
        val wrapper = ItemWrapper("test")
        assertEquals("test", wrapper.item)
        assertTrue(wrapper.extras.isEmpty())
    }
    
    @Test
    fun test_create_wrapper_with_extras() {
        val wrapper = ItemWrapper("test", mapOf("key" to "value"))
        assertEquals("test", wrapper.item)
        assertEquals("value", wrapper.getExtra("key"))
    }
    
    @Test
    fun test_get_extra() {
        val wrapper = ItemWrapper("test", mapOf("name" to "John", "age" to 30))
        
        assertEquals("John", wrapper.getExtra("name"))
        assertEquals(30, wrapper.getExtra("age"))
        assertEquals(null, wrapper.getExtra("notExists"))
    }
    
    @Test
    fun test_get_extra_or_default() {
        val wrapper = ItemWrapper("test", mapOf("name" to "John"))
        
        assertEquals("John", wrapper.getExtraOrDefault("name", "Default"))
        assertEquals("Default", wrapper.getExtraOrDefault("notExists", "Default"))
    }
    
    @Test
    fun test_has_extra() {
        val wrapper = ItemWrapper("test", mapOf("name" to "John"))
        
        assertTrue(wrapper.hasExtra("name"))
        assertFalse(wrapper.hasExtra("notExists"))
    }
    
    @Test
    fun test_builder() {
        val wrapper = ItemWrapperBuilder("test")
            .putExtra("name", "John")
            .putExtra("age", 30)
            .build()
        
        assertEquals("test", wrapper.item)
        assertEquals("John", wrapper.getExtra("name"))
        assertEquals(30, wrapper.getExtra("age"))
    }
    
    @Test
    fun test_extension_with_extras() {
        val wrapper = "test".withExtras("name" to "John", "age" to 30)
        
        assertEquals("test", wrapper.item)
        assertEquals("John", wrapper.getExtra("name"))
        assertEquals(30, wrapper.getExtra("age"))
    }
    
    @Test
    fun test_extension_wrap() {
        val wrapper = "test".wrap()
            .putExtra("name", "John")
            .build()
        
        assertEquals("test", wrapper.item)
        assertEquals("John", wrapper.getExtra("name"))
    }
}
