package com.multitype.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ItemLoaderTest {
    
    @Test
    fun test_create_from_list() {
        val loader = ItemLoaderBuilder.createFromList(listOf("a", "b", "c"))
        
        var result: List<Any>? = null
        loader.load(
            onSuccess = { result = it },
            onError = { }
        )
        
        assertEquals(3, result?.size)
    }
    
    @Test
    fun test_create_empty() {
        val loader = ItemLoaderBuilder.createEmpty()
        
        var result: List<Any>? = null
        loader.load(
            onSuccess = { result = it },
            onError = { }
        )
        
        assertTrue(result?.isEmpty() == true)
    }
    
    @Test
    fun test_create_custom() {
        var loaded = false
        val loader = ItemLoaderBuilder.create { onSuccess, _ ->
            loaded = true
            onSuccess(listOf(1, 2, 3))
        }
        
        loader.load({ }, { })
        
        assertTrue(loaded)
    }
    
    @Test
    fun test_error_callback() {
        var errorCalled = false
        val loader = ItemLoaderBuilder.create { _, onError ->
            onError(RuntimeException("Test error"))
        }
        
        loader.load(
            onSuccess = { },
            onError = { errorCalled = true }
        )
        
        assertTrue(errorCalled)
    }
}
