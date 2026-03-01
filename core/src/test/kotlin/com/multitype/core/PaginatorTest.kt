package com.multitype.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class PaginatorTest {
    
    @Test
    fun test_load_page() {
        val paginator = PaginatorBuilder.create(10)
        
        paginator.loadPage(listOf("a", "b", "c"))
        
        assertEquals(3, paginator.getTotalCount())
        assertEquals(1, paginator.getCurrentPage())
    }
    
    @Test
    fun test_has_more() {
        val paginator = PaginatorBuilder.create(2)
        
        paginator.loadPage(listOf("a", "b"))
        
        assertTrue(paginator.hasMore())
    }
    
    @Test
    fun test_no_more_when_empty() {
        val paginator = PaginatorBuilder.create(2)
        
        paginator.loadPage(emptyList())
        
        assertFalse(paginator.hasMore())
    }
    
    @Test
    fun test_reset() {
        val paginator = PaginatorBuilder.create(2)
        paginator.loadPage(listOf("a", "b"))
        
        paginator.reset()
        
        assertEquals(0, paginator.getTotalCount())
        assertEquals(0, paginator.getCurrentPage())
        assertTrue(paginator.hasMore())
    }
    
    @Test
    fun test_accumulate_pages() {
        val paginator = PaginatorBuilder.create(2)
        
        paginator.loadPage(listOf("a", "b"))
        paginator.loadPage(listOf("c", "d"))
        
        assertEquals(4, paginator.getTotalCount())
        assertEquals(2, paginator.getCurrentPage())
    }
}
