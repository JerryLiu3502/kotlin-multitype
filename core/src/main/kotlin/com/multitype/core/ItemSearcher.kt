package com.multitype.core

/**
 * Item search helper.
 */
object ItemSearcher {
    
    /**
     * Search items by query.
     */
    fun search(items: List<Any>, query: String, mapper: (Any) -> String = { it.toString() }): List<Any> {
        if (query.isBlank()) return items
        return items.filter { mapper(it).contains(query, ignoreCase = true) }
    }
    
    /**
     * Search items by predicate.
     */
    fun search(items: List<Any>, predicate: (Any) -> Boolean): List<Any> {
        return items.filter(predicate)
    }
}
