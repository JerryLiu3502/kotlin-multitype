package com.multitype.core

/**
 * Item click listener.
 */
interface ItemClickListener {
    
    /**
     * Called when an item is clicked.
     */
    fun onItemClick(position: Int, item: Any)
    
    /**
     * Called when an item is long clicked.
     */
    fun onItemLongClick(position: Int, item: Any): Boolean = false
}

/**
 * Simple implementation of ItemClickListener.
 */
class SimpleItemClickListener(
    private val onClick: (Int, Any) -> Unit,
    private val onLongClick: (Int, Any) -> Boolean = { _, _ -> false }
) : ItemClickListener {
    
    override fun onItemClick(position: Int, item: Any) {
        onClick(position, item)
    }
    
    override fun onItemLongClick(position: Int, item: Any): Boolean {
        return onLongClick(position, item)
    }
}

/**
 * Builder for ItemClickListener.
 */
object ItemClickListenerBuilder {
    
    /**
     * Create a listener with only click callback.
     */
    fun create(onClick: (Int, Any) -> Unit): ItemClickListener {
        return SimpleItemClickListener(onClick)
    }
    
    /**
     * Create a listener with click and long click callbacks.
     */
    fun create(
        onClick: (Int, Any) -> Unit,
        onLongClick: (Int, Any) -> Boolean
    ): ItemClickListener {
        return SimpleItemClickListener(onClick, onLongClick)
    }
}
