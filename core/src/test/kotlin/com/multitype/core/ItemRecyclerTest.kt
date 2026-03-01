package com.multitype.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class ItemRecyclerTest {
    
    @Test
    fun test_recycle_and_obtain() {
        val recycler = ItemRecyclerBuilder.create()
        
        recycler.recycle("item")
        val obtained = recycler.obtain()
        
        assertEquals("item", obtained)
        assertEquals(0, recycler.getRecycledCount())
    }
    
    @Test
    fun test_obtain_empty() {
        val recycler = ItemRecyclerBuilder.create()
        
        val obtained = recycler.obtain()
        
        assertEquals(null, obtained)
    }
    
    @Test
    fun test_max_size() {
        val recycler = ItemRecyclerBuilder.create(2)
        
        recycler.recycle("a")
        recycler.recycle("b")
        recycler.recycle("c")
        
        assertEquals(2, recycler.getRecycledCount())
    }
    
    @Test
    fun test_clear() {
        val recycler = ItemRecyclerBuilder.create()
        recycler.recycle("a")
        recycler.recycle("b")
        
        recycler.clear()
        
        assertEquals(0, recycler.getRecycledCount())
    }
    
    @Test
    fun test_has_recycled() {
        val recycler = ItemRecyclerBuilder.create()
        
        assertFalse(recycler.hasRecycled())
        
        recycler.recycle("item")
        
        assertTrue(recycler.hasRecycled())
    }
}
