package com.multitype.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ItemSpanSizeLookupTest {
    
    @Test
    fun test_fixed_span_size() {
        val lookup = ItemSpanSizeLookupBuilder.fixedSpanSize(2)
        
        assertEquals(2, lookup.getSpanSize(0, "item"))
        assertEquals(2, lookup.getSpanSize(1, 123))
        assertEquals(2, lookup.getSpanSize(5, Any()))
    }
    
    @Test
    fun test_custom_lookup() {
        val lookup = ItemSpanSizeLookupBuilder.create { position, _ ->
            if (position % 3 == 0) 3 else 1
        }
        
        assertEquals(3, lookup.getSpanSize(0, "test"))
        assertEquals(1, lookup.getSpanSize(1, "test"))
        assertEquals(1, lookup.getSpanSize(2, "test"))
        assertEquals(3, lookup.getSpanSize(3, "test"))
    }
}
