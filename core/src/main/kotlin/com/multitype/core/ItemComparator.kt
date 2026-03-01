package com.multitype.core

/**
 * Item comparator for comparing items.
 */
interface ItemComparator {
    fun compare(item1: Any, item2: Any): Int
}

/**
 * Simple ItemComparator implementation.
 */
class SimpleItemComparator(
    private val comparator: (Any, Any) -> Int
) : ItemComparator {
    override fun compare(item1: Any, item2: Any): Int = comparator(item1, item2)
}

/**
 * Builder for ItemComparator.
 */
object ItemComparatorBuilder {
    fun create(comparator: (Any, Any) -> Int): ItemComparator {
        return SimpleItemComparator(comparator)
    }
    
    fun createByProperty(propertyProvider: (Any) -> Comparable<*>): ItemComparator {
        return SimpleItemComparator { item1, item2 ->
            val p1 = propertyProvider(item1)
            val p2 = propertyProvider(item2)
            p1.compareTo(p2)
        }
    }
}
