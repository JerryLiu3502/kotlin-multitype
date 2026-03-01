package com.multitype.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class ItemCacheTest {
    
    @Test
    fun test_put_and_get() {
        val cache = ItemCacheBuilder.create()
        
        cache.put("key", "value")
        
        assertEquals("value", cache.get("key"))
    }
    
    @Test
    fun test_get_non_existent() {
        val cache = ItemCacheBuilder.create()
        
        assertNull(cache.get("non_existent"))
    }
    
    @Test
    fun test_contains() {
        val cache = ItemCacheBuilder.create()
        cache.put("key", "value")
        
        assertTrue(cache.contains("key"))
    }
    
    @Test
    fun test_remove() {
        val cache = ItemCacheBuilder.create()
        cache.put("key", "value")
        
        cache.remove("key")
        
        assertNull(cache.get("key"))
    }
    
    @Test
    fun test_clear() {
        val cache = ItemCacheBuilder.create()
        cache.put("a", 1)
        cache.put("b", 2)
        
        cache.clear()
        
        assertEquals(0, cache.size())
    }
    
    @Test
    fun test_max_size() {
        val cache = ItemCacheBuilder.create(2)
        cache.put("a", 1)
        cache.put("b", 2)
        cache.put("c", 3)
        
        assertEquals(2, cache.size())
        assertNull(cache.get("a"))
    }
}
