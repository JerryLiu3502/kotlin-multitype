package com.multitype.core

/**
 * Item span size lookup for GridLayoutManager.
 */
class ItemSpanSizeLookup(
    private val getSpanSize: (Int, Any) -> Int
) {
    /**
     * Get the span size for a position.
     */
    fun getSpanSize(position: Int, item: Any): Int {
        return getSpanSize(position, item)
    }
}

/**
 * Builder for ItemSpanSizeLookup.
 */
object ItemSpanSizeLookupBuilder {
    
    /**
     * Create a fixed span size lookup.
     */
    fun fixedSpanSize(spanSize: Int): ItemSpanSizeLookup {
        return ItemSpanSizeLookup { _, _ -> spanSize }
    }
    
    /**
     * Create a lookup based on item type.
     */
    fun byType(spans: Map<Int, Int>, defaultSpan: Int = 1): ItemSpanSizeLookup {
        return ItemSpanSizeLookup { _, item ->
            val clazz = item::class.java
            val hash = clazz.hashCode()
            spans[hash] ?: defaultSpan
        }
    }
    
    /**
     * Create a lookup based on item class.
     */
    fun byClass(spans: Map<Class<*>, Int>, defaultSpan: Int = 1): ItemSpanSizeLookup {
        return ItemSpanSizeLookup { _, item ->
            spans[item::class.java] ?: defaultSpan
        }
    }
}

/**
 * Extension function to create span size lookup.
 */
inline fun createSpanSizeLookup(crossinline getSpanSize: (Int, Any) -> Int): ItemSpanSizeLookup {
    return ItemSpanSizeLookup { position, item ->
        getSpanSize(position, item)
    }
}
