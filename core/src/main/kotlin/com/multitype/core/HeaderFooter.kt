package com.multitype.core

/**
 * Helper class for creating header and footer items.
 */
object HeaderFooter {
    
    /**
     * Create a header item with content.
     */
    fun createHeader(content: Any): HeaderItem {
        return HeaderItem(content, true)
    }
    
    /**
     * Create a footer item with content.
     */
    fun createFooter(content: Any): FooterItem {
        return FooterItem(content, false)
    }
    
    /**
     * Check if an item is a header.
     */
    fun isHeader(item: Any): Boolean {
        return item is HeaderItem
    }
    
    /**
     * Check if an item is a footer.
     */
    fun isFooter(item: Any): Boolean {
        return item is FooterItem
    }
    
    /**
     * Check if an item is a header or footer.
     */
    fun isHeaderOrFooter(item: Any): Boolean {
        return item is HeaderItem || item is FooterItem
    }
}

/**
 * Header item wrapper.
 */
class HeaderItem(
    val content: Any,
    val isHeader: Boolean = true
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is HeaderItem) return false
        return content == other.content && isHeader == other.isHeader
    }
    
    override fun hashCode(): Int {
        return content.hashCode() + isHeader.hashCode()
    }
}

/**
 * Footer item wrapper.
 */
class FooterItem(
    val content: Any,
    val isFooter: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FooterItem) return false
        return content == other.content && isFooter == other.isFooter
    }
    
    override fun hashCode(): Int {
        return content.hashCode() + isFooter.hashCode()
    }
}

/**
 * Extension function to add header to adapter.
 */
fun <T : ItemBinder> MultiTypeRecyclerViewAdapter.withHeader(
    headerBinder: T,
    headerContent: Any
): MultiTypeRecyclerViewAdapter {
    register(headerBinder)
    insertItem(0, HeaderItem(headerContent))
    return this
}

/**
 * Extension function to add footer to adapter.
 */
fun <T : ItemBinder> MultiTypeRecyclerViewAdapter.withFooter(
    footerBinder: T,
    footerContent: Any
): MultiTypeRecyclerViewAdapter {
    register(footerBinder)
    addItem(FooterItem(footerContent))
    return this
}
