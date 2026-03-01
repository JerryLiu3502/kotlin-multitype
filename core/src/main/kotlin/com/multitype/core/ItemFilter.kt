package com.multitype.core

/**
 * Item filter for filtering items.
 */
interface ItemFilter {
    
    /**
     * Check if item should be included.
     */
    fun accept(item: Any): Boolean
}

/**
 * Simple implementation of ItemFilter.
 */
class SimpleItemFilter(
    private val predicate: (Any) -> Boolean
) : ItemFilter {
    
    override fun accept(item: Any): Boolean {
        return predicate(item)
    }
}

/**
 * Builder for ItemFilter.
 */
object ItemFilterBuilder {
    
    /**
     * Create with predicate.
     */
    fun create(predicate: (Any) -> Boolean): ItemFilter {
        return SimpleItemFilter(predicate)
    }
    
    /**
     * Create by class.
     */
    fun createByClass(vararg classes: Class<*>): ItemFilter {
        return SimpleItemFilter { item ->
            classes.any { it.isInstance(item) }
        }
    }
    
    /**
     * Create exclude by class.
     */
    fun excludeByClass(vararg classes: Class<*>): ItemFilter {
        return SimpleItemFilter { item ->
            classes.none { it.isInstance(item) }
        }
    }
}
