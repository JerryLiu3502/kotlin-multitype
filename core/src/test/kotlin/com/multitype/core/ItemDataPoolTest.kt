package com.multitype.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class ItemDataPoolTest {
    
    @Test
    fun test_create_pool() {
        val pool = ItemDataPool()
        assertTrue(pool.isEmpty())
        assertEquals(0, pool.size())
    }
    
    @Test
    fun test_create_pool_with_capacity() {
        val pool = ItemDataPool(20)
        assertTrue(pool.isEmpty())
    }
    
    @Test
    fun test_add_item() {
        val pool = ItemDataPool()
        pool.add(ItemData("test", 0, 1))
        
        assertEquals(1, pool.size())
        assertFalse(pool.isEmpty())
    }
    
    @Test
    fun test_get_item() {
        val pool = ItemDataPool()
        val itemData = ItemData("test", 0, 1)
        pool.add(itemData)
        
        val retrieved = pool.get(0)
        assertEquals(itemData, retrieved)
    }
    
    @Test
    fun test_get_all() {
        val pool = ItemDataPool()
        pool.add(ItemData("item1", 0, 1))
        pool.add(ItemData("item2", 1, 2))
        
        val all = pool.getAll()
        assertEquals(2, all.size)
    }
    
    @Test
    fun test_clear() {
        val pool = ItemDataPool()
        pool.add(ItemData("test", 0, 1))
        pool.clear()
        
        assertTrue(pool.isEmpty())
    }
    
    @Test
    fun test_item_data_equals() {
        val item1 = ItemData("test", 0, 1)
        val item2 = ItemData("test", 0, 1)
        
        assertEquals(item1, item2)
    }
}
