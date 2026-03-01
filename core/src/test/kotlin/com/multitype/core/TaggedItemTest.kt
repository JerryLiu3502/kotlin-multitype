package com.multitype.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TaggedItemTest {
    
    @Test
    fun test_create_tagged_item() {
        val tagged = TaggedItemHelper.create("item", "tag1")
        
        assertEquals("item", tagged.item)
        assertEquals("tag1", tagged.tag)
    }
    
    @Test
    fun test_filter_by_tag() {
        val items = listOf(
            TaggedItem("a", "tag1"),
            TaggedItem("b", "tag2"),
            TaggedItem("c", "tag1")
        )
        
        val filtered = TaggedItemHelper.filterByTag(items, "tag1")
        
        assertEquals(2, filtered.size)
        assertTrue(filtered.all { it.tag == "tag1" })
    }
    
    @Test
    fun test_get_unique_tags() {
        val items = listOf(
            TaggedItem("a", "tag1"),
            TaggedItem("b", "tag2"),
            TaggedItem("c", "tag1"),
            TaggedItem("d", "tag3")
        )
        
        val tags = TaggedItemHelper.getUniqueTags(items)
        
        assertEquals(3, tags.size)
        assertTrue(tags.contains("tag1"))
        assertTrue(tags.contains("tag2"))
        assertTrue(tags.contains("tag3"))
    }
    
    @Test
    fun test_group_by_tag() {
        val items = listOf(
            TaggedItem("a", "tag1"),
            TaggedItem("b", "tag2"),
            TaggedItem("c", "tag1")
        )
        
        val grouped = TaggedItemHelper.groupByTag(items)
        
        assertEquals(2, grouped.size)
        assertEquals(2, grouped["tag1"]?.size)
        assertEquals(1, grouped["tag2"]?.size)
    }
}
