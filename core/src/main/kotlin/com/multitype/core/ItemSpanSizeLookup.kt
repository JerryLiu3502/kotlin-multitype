package com.multitype.core

/**
 * Item span size lookup for GridLayoutManager.
 */
class ItemSpanSizeLookup(
    private val spanSizeProvider: (Int, Any) -> Int
) {
    /**
     * Get span size for position.
     */
    fun getSpanSize(position: Int, item: Any): Int {
        return spanSizeProvider(position, item)
    }
}

/**
 * Builder for ItemSpanSizeLookup.
 */
object ItemSpanSizeLookupBuilder {
    
    /**
     * Create with fixed span size.
     */
    fun fixedSpanSize(spanSize: Int): ItemSpanSizeLookup {
        return ItemSpanSizeLookup { _, _ -> spanSize }
    }
    
    /**
     * Create with custom lookup function.
     */
    fun create(spanSizeProvider: (Int, Any) -> Int): ItemSpanSizeLookup {
        return ItemSpanSizeLookup(spanSizeProvider)
    }
}
