package com.multitype.core

import kotlin.test.Test
import kotlin.test.assertTrue

class ItemDecorationTest {
    
    @Test
    fun test_simple_decoration() {
        val decoration = object : SimpleItemDecoration() {}
        assertTrue(decoration != null)
    }
    
    @Test
    fun test_spacing_decoration() {
        val decoration = ItemDecorationBuilder.createSpacing(16)
        assertTrue(decoration != null)
    }
    
    @Test
    fun test_divider_decoration() {
        val decoration = ItemDecorationBuilder.createDivider(1, 0xFF000000.toInt())
        assertTrue(decoration != null)
    }
    
    @Test
    fun test_decoration_with_override() {
        var called = false
        val decoration = object : SimpleItemDecoration() {
            override fun onDraw(canvas: Any, parent: Any, state: Any) {
                called = true
            }
        }
        
        decoration.onDraw("canvas", "parent", "state")
        assertTrue(called)
    }
    
    @Test
    fun test_on_draw_over() {
        var called = false
        val decoration = object : SimpleItemDecoration() {
            override fun onDrawOver(canvas: Any, parent: Any, state: Any) {
                called = true
            }
        }
        
        decoration.onDrawOver("canvas", "parent", "state")
        assertTrue(called)
    }
}
