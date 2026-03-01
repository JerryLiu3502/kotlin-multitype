package com.multitype.core

/**
 * Base class for multi-type item binders.
 * Each binder handles a specific type of content.
 * 
 * This is similar to Drakeet's ItemViewBinder.
 */
abstract class ItemBinder {
    
    /**
     * Returns the class type this binder handles.
     */
    abstract fun getItemClass(): Class<*>
    
    /**
     * Returns the content type identifier for this binder.
     * Default implementation uses hashCode of the class.
     */
    open fun getContentType(): Int = getItemClass().hashCode()
    
    /**
     * Check if this binder can handle the given item.
     */
    open fun canHandle(item: Any): Boolean {
        return getItemClass().isInstance(item)
    }
    
    /**
     * Bind data to the ViewHolder.
     * 
     * @param holder The ViewHolder
     * @param item The item data
     * @param position The position in the adapter
     */
    abstract fun bind(holder: Any, item: Any, position: Int)
    
    /**
     * Called when a ViewHolder is recycled.
     * 
     * @param holder The ViewHolder that was recycled
     */
    open fun onViewRecycled(holder: Any) {}
    
    /**
     * Called when a ViewHolder is attached to the window.
     * 
     * @param holder The ViewHolder that was attached
     */
    open fun onViewAttachedToWindow(holder: Any) {}
    
    /**
     * Called when a ViewHolder is detached from the window.
     * 
     * @param holder The ViewHolder that was detached
     */
    open fun onViewDetachedFromWindow(holder: Any) {}
    
    /**
     * Called when the binding is failed.
     * 
     * @return true if the exception was handled, false otherwise
     */
    open fun onFailedToBindView(holder: Any, item: Any, position: Int): Boolean = false
}

/**
 * Simple data class for items.
 */
data class Item(val content: String, val type: Int)
