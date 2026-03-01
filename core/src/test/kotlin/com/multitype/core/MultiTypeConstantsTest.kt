package com.multitype.core

import kotlin.test.Test
import kotlin.test.assertEquals

class MultiTypeConstantsTest {
    
    @Test
    fun test_default_view_type() {
        assertEquals(0, MultiTypeConstants.DEFAULT_VIEW_TYPE)
    }
    
    @Test
    fun test_invalid_position() {
        assertEquals(-1, MultiTypeConstants.INVALID_POSITION)
    }
    
    @Test
    fun test_span_sizes() {
        assertEquals(1, MultiTypeConstants.MIN_SPAN_SIZE)
        assertEquals(100, MultiTypeConstants.MAX_SPAN_SIZE)
    }
    
    @Test
    fun test_animation_duration() {
        assertEquals(300L, MultiTypeConstants.DEFAULT_ANIMATION_DURATION)
    }
    
    @Test
    fun test_view_type_constants() {
        assertEquals(-1, ViewTypeConstants.HEADER_VIEW_TYPE)
        assertEquals(-2, ViewTypeConstants.FOOTER_VIEW_TYPE)
        assertEquals(-3, ViewTypeConstants.EMPTY_VIEW_TYPE)
        assertEquals(-4, ViewTypeConstants.LOADING_VIEW_TYPE)
        assertEquals(-5, ViewTypeConstants.ERROR_VIEW_TYPE)
    }
}
