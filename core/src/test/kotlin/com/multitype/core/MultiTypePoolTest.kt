package com.multitype.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class MultiTypePoolTest {
    
    @Test
    fun test_create_pool() {
        val pool = MultiTypePool()
        assertEquals(0, pool.size())
    }
    
    @Test
    fun test_get_adapter() {
        val pool = MultiTypePool()
        val adapter = pool.getAdapter("main")
        
        assertTrue(adapter is MultiTypeRecyclerViewAdapter)
        assertEquals(1, pool.size())
    }
    
    @Test
    fun test_get_same_adapter() {
        val pool = MultiTypePool()
        val adapter1 = pool.getAdapter("main")
        val adapter2 = pool.getAdapter("main")
        
        assertTrue(adapter1 === adapter2)
    }
    
    @Test
    fun test_get_different_adapters() {
        val pool = MultiTypePool()
        val adapter1 = pool.getAdapter("main")
        val adapter2 = pool.getAdapter("detail")
        
        assertTrue(adapter1 !== adapter2)
        assertEquals(2, pool.size())
    }
    
    @Test
    fun test_remove_adapter() {
        val pool = MultiTypePool()
        pool.getAdapter("main")
        
        val removed = pool.removeAdapter("main")
        assertTrue(removed)
        assertEquals(0, pool.size())
    }
    
    @Test
    fun test_clear() {
        val pool = MultiTypePool()
        pool.getAdapter("main")
        pool.getAdapter("detail")
        
        pool.clear()
        assertEquals(0, pool.size())
    }
    
    @Test
    fun test_get_adapter_names() {
        val pool = MultiTypePool()
        pool.getAdapter("main")
        pool.getAdapter("detail")
        
        val names = pool.getAdapterNames()
        assertEquals(2, names.size)
        assertTrue(names.contains("main"))
        assertTrue(names.contains("detail"))
    }
    
    @Test
    fun test_global_pool() {
        GlobalMultiTypePool.clear()
        
        val adapter = GlobalMultiTypePool.getAdapter("test")
        assertTrue(adapter is MultiTypeRecyclerViewAdapter)
        
        GlobalMultiTypePool.clear()
    }
}
