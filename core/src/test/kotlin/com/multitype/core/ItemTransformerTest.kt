package com.multitype.core

import kotlin.test.Test
import kotlin.test.assertEquals

class ItemTransformerTest {
    
    @Test
    fun test_custom_transformer() {
        val transformer = ItemTransformerBuilder.create { i: Int -> i * 2 }
        
        assertEquals(4, transformer.transform(2))
    }
    
    @Test
    fun test_to_string() {
        val transformer = ItemTransformerBuilder.toString()
        
        assertEquals("123", transformer.transform(123))
    }
    
    @Test
    fun test_to_list() {
        val transformer = ItemTransformerBuilder.toList<String>()
        
        assertEquals(listOf("test"), transformer.transform("test"))
    }
    
    @Test
    fun test_type_transform() {
        val transformer = ItemTransformerBuilder.create { i: Int -> "Number: $i" }
        
        assertEquals("Number: 5", transformer.transform(5))
    }
}
