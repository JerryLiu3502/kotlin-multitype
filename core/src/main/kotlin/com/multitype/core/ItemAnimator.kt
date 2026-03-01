package com.multitype.core

/**
 * Item animator for animating item changes.
 */
interface ItemAnimator {
    
    /**
     * Called when an item is added.
     */
    fun onItemAdded(position: Int, item: Any)
    
    /**
     * Called when an item is removed.
     */
    fun onItemRemoved(position: Int, item: Any)
    
    /**
     * Called when an item is moved.
     */
    fun onItemMoved(fromPosition: Int, toPosition: Int)
    
    /**
     * Called when an item is changed.
     */
    fun onItemChanged(position: Int, oldItem: Any, newItem: Any)
}

/**
 * Simple implementation of ItemAnimator with empty methods.
 */
open class SimpleItemAnimator : ItemAnimator {
    
    override fun onItemAdded(position: Int, item: Any) {
        // Override to implement
    }
    
    override fun onItemRemoved(position: Int, item: Any) {
        // Override to implement
    }
    
    override fun onItemMoved(fromPosition: Int, toPosition: Int) {
        // Override to implement
    }
    
    override fun onItemChanged(position: Int, oldItem: Any, newItem: Any) {
        // Override to implement
    }
}

/**
 * Builder for ItemAnimator.
 */
object ItemAnimatorBuilder {
    
    /**
     * Create an ItemAnimator with callbacks.
     */
    fun create(
        onAdded: ((Int, Any) -> Unit)? = null,
        onRemoved: ((Int, Any) -> Unit)? = null,
        onMoved: ((Int, Int) -> Unit)? = null,
        onChanged: ((Int, Any, Any) -> Unit)? = null
    ): ItemAnimator {
        return object : ItemAnimator {
            override fun onItemAdded(position: Int, item: Any) {
                onAdded?.invoke(position, item)
            }
            
            override fun onItemRemoved(position: Int, item: Any) {
                onRemoved?.invoke(position, item)
            }
            
            override fun onItemMoved(fromPosition: Int, toPosition: Int) {
                onMoved?.invoke(fromPosition, toPosition)
            }
            
            override fun onItemChanged(position: Int, oldItem: Any, newItem: Any) {
                onChanged?.invoke(position, oldItem, newItem)
            }
        }
    }
}
