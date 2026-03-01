package com.multitype.core

/**
 * Item sorter for sorting items.
 */
interface ItemSorter {
    
    /**
     * Compare two items.
     */
    fun compare(item1: Any, item2: Any): Int
}

/**
 * Simple implementation of ItemSorter.
 */
class SimpleItemSorter(
    private val comparator: (Any, Any) -> Int
) : ItemSorter {
    
    override fun compare(item1: Any, item2: Any): Int {
        return comparator(item1, item2)
    }
}

/**
 * Builder for ItemSorter.
 */
object ItemSorterBuilder {
    
    /**
     * Create with comparator.
     */
    fun create(comparator: (Any, Any) -> Int): ItemSorter {
        return SimpleItemSorter(comparator)
    }
    
    /**
     * Create ascending sorter.
     */
    fun createAscending(): ItemSorter {
        return SimpleItemSorter { item1, item2 ->
            when {
                item1 is Comparable<*> && item2 is Comparable<*> -> {
                    @Suppress("UNCHECKED_CAST")
                    (item1 as Comparable<Any>).compareTo(item2)
                }
                else -> 0
            }
        }
    }
    
    /**
     * Create descending sorter.
     */
    fun createDescending(): ItemSorter {
        return SimpleItemSorter { item1, item2 ->
            when {
                item1 is Comparable<*> && item2 is Comparable<*> -> {
                    @Suppress("UNCHECKED_CAST")
                    (item2 as Comparable<Any>).compareTo(item1)
                }
                else -> 0
            }
        }
    }
}
