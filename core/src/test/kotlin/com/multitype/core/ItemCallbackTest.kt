package com.multitype.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ItemCallbackTest {
    
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
        val callback = object : ItemCallback() {
            override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean = true
            override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean = true
        }
        
        assertEquals(null, callback.getChangePayload("old", "new"))
    }
}
