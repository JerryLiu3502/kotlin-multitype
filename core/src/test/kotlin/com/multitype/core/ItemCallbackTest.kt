package com.multitype.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class ItemCallbackTest {
    
    @Test
    fun test_simple_callback() {
        val callback = SimpleItemCallback(
            areItemsTheSame = { old, new -> old::class.java == new::class.java },
            areContentsTheSame = { old, new -> old == new }
        )
        
        // Both "a" and "b" are Strings, so same type
        assertTrue(callback.areItemsTheSame("a", "b"))
        // "a" is String, 1 is Int, so different types
        assertFalse(callback.areItemsTheSame("a", 1))
        // "a" != "b"
        assertFalse(callback.areContentsTheSame("a", "b"))
        // "a" == "a"
        assertTrue(callback.areContentsTheSame("a", "a"))
    }
    
    @Test
    fun test_builder_with_both_functions() {
        val callback = ItemCallbackBuilder.create(
            areItemsTheSame = { old, new -> old::class.java == new::class.java },
            areContentsTheSame = { old, new -> old == new }
        )
        
        assertTrue(callback.areItemsTheSame("a", "b"))
        assertTrue(callback.areContentsTheSame("a", "a"))
    }
    
    @Test
    fun test_builder_with_single_function() {
        val callback = ItemCallbackBuilder.create(
            areItemsTheSame = { old, new -> old::class.java == new::class.java }
        )
        
        assertTrue(callback.areItemsTheSame("a", "b"))
        assertTrue(callback.areContentsTheSame("a", "a"))
        assertFalse(callback.areContentsTheSame("a", "b"))
    }
    
    @Test
    fun test_get_change_payload() {
        val callback = object : ItemCallback() {
            override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean = true
            override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean = false
            override fun getChangePayload(oldItem: Any, newItem: Any): Any? = "payload"
        }
        
        assertEquals("payload", callback.getChangePayload("old", "new"))
    }
    
    @Test
    fun test_default_get_change_payload() {
        val callback = SimpleItemCallback(
            areItemsTheSame = { old, new -> true },
            areContentsTheSame = { old, new -> true }
        )
        
        assertEquals(null, callback.getChangePayload("old", "new"))
    }
}
