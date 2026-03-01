package com.multitype.core

/**
 * Item group for grouping items.
 */
data class ItemGroup(
    val name: String,
    val items: List<Any>
)

/**
 * Helper for grouping items.
 */
object ItemGrouper {
    
    /**
     * Group items by a key.
     */
    fun groupBy(items: List<Any>, keyProvider: (Any) -> String): List<ItemGroup> {
        return items.groupBy(keyProvider).map { (key, group) ->
            ItemGroup(key, group)
        }
    }
    
    /**
     * Group items by type.
     */
    fun groupByType(items: List<Any>): List<ItemGroup> {
        return items.groupBy { 
            when (it) {
                is String -> "String"
                is Int -> "Int"
                is Long -> "Long"
                is Double -> "Double"
                is Float -> "Float"
                is Boolean -> "Boolean"
                else -> "Other"
            }
        }.map { (key, group) ->
            ItemGroup(key, group)
        }
    }
}
