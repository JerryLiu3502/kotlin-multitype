package com.multitype.core

import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.assertEquals

class ItemComparatorTest {
    
    @Test
    fun test_create_comparator() {
        val comparator = ItemComparatorBuilder.create { i1: Int, i2: Int -> i1 - i2 }
        
        assertTrue(comparator.compare(2, 1) > 0)
        assertTrue(comparator.compare(1, 2) < 0)
        assertEquals(0, comparator.compare(1, 1))
    }
    
    @Test
    fun test_create_by_property() {
        data class Person(val name: String, val age: Int)
        
        val comparator = ItemComparatorBuilder.createByProperty { (it as Person).age }
        
        assertTrue(comparator.compare(Person("A", 20), Person("B", 10)) > 0)
        assertTrue(comparator.compare(Person("A", 10), Person("B", 20)) < 0)
    }
    
    @Test
    fun test_string_comparator() {
        val comparator = ItemComparatorBuilder.create { s1: String, s2: String -> s1.compareTo(s2) }
        
        assertTrue(comparator.compare("b", "a") > 0)
        assertTrue(comparator.compare("a", "b") < 0)
    }
}
