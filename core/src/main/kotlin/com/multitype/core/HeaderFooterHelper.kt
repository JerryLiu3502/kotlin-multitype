package com.multitype.core

/**
 * Header footer helper for managing headers and footers.
 */
class HeaderFooterHelper {
    
    private val headers = mutableListOf<Any>()
    private val footers = mutableListOf<Any>()
    
    /**
     * Add a header.
     */
    fun addHeader(header: Any) {
        headers.add(header)
    }
    
    /**
     * Remove a header.
     */
    fun removeHeader(header: Any): Boolean {
        return headers.remove(header)
    }
    
    /**
     * Add a footer.
     */
    fun addFooter(footer: Any) {
        footers.add(footer)
    }
    
    /**
     * Remove a footer.
     */
    fun removeFooter(footer: Any): Boolean {
        return footers.remove(footer)
    }
    
    /**
     * Get header count.
     */
    fun getHeaderCount(): Int = headers.size
    
    /**
     * Get footer count.
     */
    fun getFooterCount(): Int = footers.size
    
    /**
     * Get all headers.
     */
    fun getHeaders(): List<Any> = headers.toList()
    
    /**
     * Get all footers.
     */
    fun getFooters(): List<Any> = footers.toList()
    
    /**
     * Clear all headers and footers.
     */
    fun clear() {
        headers.clear()
        footers.clear()
    }
    
    /**
     * Check if has headers.
     */
    fun hasHeader(): Boolean = headers.isNotEmpty()
    
    /**
     * Check if has footers.
     */
    fun hasFooter(): Boolean = footers.isNotEmpty()
}

/**
 * Builder for HeaderFooterHelper.
 */
object HeaderFooterHelperBuilder {
    
    /**
     * Create a HeaderFooterHelper.
     */
    fun create(): HeaderFooterHelper {
        return HeaderFooterHelper()
    }
}
