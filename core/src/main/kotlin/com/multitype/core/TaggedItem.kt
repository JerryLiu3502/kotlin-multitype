package com.multitype.core

/**
 * Wrapper for items with tag.
 */
data class TaggedItem(
    val item: Any,
    val tag: String
)

/**
 * Helper for managing tagged items.
 */
object TaggedItemHelper {
    
    /**
     * Create a TaggedItem.
     */
    fun create(item: Any, tag: String): TaggedItem {
        return TaggedItem(item, tag)
    }
    
    /**
     * Filter items by tag.
     */
    fun filterByTag(items: List<TaggedItem>, tag: String): List<TaggedItem> {
        return items.filter { it.tag == tag }
    }
    
    /**
     * Get unique tags.
     */
    fun getUniqueTags(items: List<TaggedItem>): List<String> {
        return items.map { it.tag }.distinct()
    }
    
    /**
     * Group items by tag.
     */
    fun groupByTag(items: List<TaggedItem>): Map<String, List<TaggedItem>> {
        return items.groupBy { it.tag }
    }
}
