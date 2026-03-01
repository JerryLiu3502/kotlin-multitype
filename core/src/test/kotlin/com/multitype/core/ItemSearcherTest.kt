package com.multitype.core

import kotlin.test.Test
import kotlin.test.assertEquals

class ItemSearcherTest {
    
    @Test
    fun test_search_by_query() {
        val items = listOf("apple", "banana", "apricot", "orange")
        
        val result = ItemSearcher.search(items, "ap")
        
        assertEquals(2, result.size)
    }
    
    @Test
    fun test_search_empty_query() {
        val items = listOf("apple", "banana")
        
        val result = ItemSearcher.search(items, "")
        
        assertEquals(2, result.size)
    }
    
    @Test
    fun test_search_with_mapper() {
        val items = listOf(
            mapOf("name" to "apple"),
            mapOf("name" to "banana")
        )
        
        val result = ItemSearcher.search(items, "apple") { (it["name"] as String) }
        
        assertEquals(1, result.size)
    }
    
    @Test
    fun test_search_by_predicate() {
        val items = listOf(1, 2, 3, 4, 5)
        
        val result = ItemSearcher.search(items) { (it as Int) > 3 }
        
        assertEquals(2, result.size)
    }
}
