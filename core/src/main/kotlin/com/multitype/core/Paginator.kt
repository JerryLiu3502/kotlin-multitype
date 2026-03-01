package com.multitype.core

/**
 * Paginator for handling pagination.
 */
class Paginator(private val pageSize: Int) {
    private var currentPage = 0
    private var items = mutableListOf<Any>()
    private var hasMore = true
    
    /**
     * Load a page of items.
     */
    fun loadPage(newItems: List<Any>) {
        if (newItems.isEmpty()) {
            hasMore = false
            return
        }
        
        items.addAll(newItems)
        currentPage++
        hasMore = newItems.size >= pageSize
    }
    
    /**
     * Get all loaded items.
     */
    fun getItems(): List<Any> = items.toList()
    
    /**
     * Get current page.
     */
    fun getCurrentPage(): Int = currentPage
    
    /**
     * Check if has more pages.
     */
    fun hasMore(): Boolean = hasMore
    
    /**
     * Reset pagination.
     */
    fun reset() {
        currentPage = 0
        items.clear()
        hasMore = true
    }
    
    /**
     * Get total items count.
     */
    fun getTotalCount(): Int = items.size
    
    /**
     * Get page size.
     */
    fun getPageSize(): Int = pageSize
}

/**
 * Builder for Paginator.
 */
object PaginatorBuilder {
    fun create(pageSize: Int): Paginator = Paginator(pageSize)
}
