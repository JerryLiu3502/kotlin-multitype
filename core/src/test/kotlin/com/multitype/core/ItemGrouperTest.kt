package com.multitype.core

import kotlin.test.Test
import kotlin.test.assertEquals

class ItemGrouperTest {
    
    @Test
    fun test_group_by() {
        val items = listOf(
            mapOf("type" to "A"),
            mapOf("type" to "B"),
            mapOf("type" to "A")
        )
        
        val groups = ItemGrouper.groupBy(items) { (it["type"] as String) }
        
        assertEquals(2, groups.size)
    }
    
    @Test
    fun test_group_by_type() {
        val items = listOf("a", 1, "b", 2, 3.0)
        
        val groups = ItemGrouper.groupByType(items)
        
        assertEquals(3, groups.size)
        assertEquals(2, groups.find { it.name == "String" }?.items?.size)
        assertEquals(2, groups.find { it.name == "Int" }?.items?.size)
        assertEquals(1, groups.find { it.name == "Double" }?.items?.size)
    }
    
    @Test
    fun test_empty_list() {
        val groups = ItemGrouper.groupByType(emptyList())
        
        assertEquals(0, groups.size)
    }
}
