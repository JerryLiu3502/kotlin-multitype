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
     * Create by type check.
     */
    fun createByType(vararg types: String): ItemFilter {
        return SimpleItemFilter { item ->
            types.any { type ->
                when (type) {
                    "String" -> item is String
                    "Int" -> item is Int
                    "Long" -> item is Long
                    "Double" -> item is Double
                    "Float" -> item is Float
                    "Boolean" -> item is Boolean
                    else -> false
                }
            }
        }
    }
    
    /**
     * Create exclude by type.
     */
    fun excludeByType(vararg types: String): ItemFilter {
        return SimpleItemFilter { item ->
            types.none { type ->
                when (type) {
                    "String" -> item is String
                    "Int" -> item is Int
                    "Long" -> item is Long
                    "Double" -> item is Double
                    "Float" -> item is Float
                    "Boolean" -> item is Boolean
                    else -> false
                }
            }
        }
    }
}
