package com.multitype.core

/**
 * Handler for showing empty view when adapter has no items.
 */
class EmptyViewHandler {
    
    private var isShowingEmpty = false
    
    /**
     * Show the empty view.
     */
    fun showEmptyView() {
        isShowingEmpty = true
    }
    
    /**
     * Show the content view.
     */
    fun showContentView() {
        isShowingEmpty = false
    }
    
    /**
     * Update the view based on item count.
     */
    fun update(itemCount: Int) {
        if (itemCount == 0) {
            showEmptyView()
        } else {
            showContentView()
        }
    }
    
    /**
     * Check if currently showing empty view.
     */
    fun isShowingEmptyView(): Boolean = isShowingEmpty
    
    /**
     * Check if showing content view.
     */
    fun isShowingContentView(): Boolean = !isShowingEmpty
}

/**
 * Builder for EmptyViewHandler.
 */
object EmptyViewHandlerBuilder {
    
    /**
     * Create an EmptyViewHandler.
     */
    fun create(): EmptyViewHandler {
        return EmptyViewHandler()
    }
}
