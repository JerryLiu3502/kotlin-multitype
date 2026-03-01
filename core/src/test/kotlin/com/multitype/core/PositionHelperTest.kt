package com.multitype.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PositionHelperTest {
    
    @Test
    fun test_create_position() {
        val position = PositionHelper.create("item", 5, 1)
        
        assertEquals("item", position.item)
        assertEquals(5, position.position)
        assertEquals(1, position.viewType)
    }
    
    @Test
    fun test_create_position_default_view_type() {
        val position = PositionHelper.create("item", 5)
        
        assertEquals("item", position.item)
        assertEquals(5, position.position)
        assertEquals(0, position.viewType)
    }
    
    @Test
    fun test_get_position() {
        val positions = listOf(
            Position("a", 0, 0),
            Position("b", 1, 0),
            Position("c", 2, 1)
        )
        
        assertEquals(0, PositionHelper.getPosition(positions, "a"))
        assertEquals(1, PositionHelper.getPosition(positions, "b"))
        assertEquals(null, PositionHelper.getPosition(positions, "not exist"))
    }
    
    @Test
    fun test_filter_by_view_type() {
        val positions = listOf(
            Position("a", 0, 0),
            Position("b", 1, 1),
            Position("c", 2, 1)
        )
        
        val filtered = PositionHelper.filterByViewType(positions, 1)
        
        assertEquals(2, filtered.size)
        assertTrue(filtered.all { it.viewType == 1 })
    }
    
    @Test
    fun test_get_positions() {
        val items = listOf("a", "b", "c")
        
        val positions = PositionHelper.getPositions(items)
        
        assertEquals(3, positions.size)
        assertEquals(0, positions[0].position)
        assertEquals(1, positions[1].position)
        assertEquals(2, positions[2].position)
    }
    
    @Test
    fun test_get_positions_with_view_type_provider() {
        val items = listOf("a", 1, "b")
        
        val positions = PositionHelper.getPositions(items) { item ->
            when (item) {
                is String -> 0
                is Int -> 1
                else -> 0
            }
        }
        
        assertEquals(0, positions[0].viewType)
        assertEquals(1, positions[1].viewType)
        assertEquals(0, positions[2].viewType)
    }
}
