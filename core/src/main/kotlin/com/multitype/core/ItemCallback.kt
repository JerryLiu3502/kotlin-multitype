package com.multitype.core

/**
 * Callback for calculating the difference between two lists.
 * 
 * This is similar to Drakeet's ItemCallback.
 */
abstract class ItemCallback {
    
    /**
     * Called to check whether two items have the same data.
     * 
     * @param oldItem The old item
     * @param newItem The new item
     * @return True if the items have the same data
     */
    abstract fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean
    
    /**
     * Called to check whether two items have the same data.
     * 
     * @param oldItem The old item
     * @param newItem The new item
     * @return True if the items have the same content
     */
    abstract fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean
    
    /**
     * Returns the payload for the change.
     * 
     * @param oldItem The old item
     * @param newItem The new item
     * @return The payload, or null if no payload
     */
    open fun getChangePayload(oldItem: Any, newItem: Any): Any? = null
}

/**
 * Simple ItemCallback implementation using lambda functions.
 */
class SimpleItemCallback(
    private val areItemsTheSame: (Any, Any) -> Boolean,
    private val areContentsTheSame: (Any, Any) -> Boolean
) : ItemCallback() {
    
    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        return areItemsTheSame(oldItem, newItem)
    }
    
    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        return areContentsTheSame(oldItem, newItem)
    }
}

/**
 * Builder for ItemCallback.
 */
object ItemCallbackBuilder {
    
    /**
     * Create an ItemCallback with custom equality checks.
     */
    fun create(
        areItemsTheSame: (Any, Any) -> Boolean,
        areContentsTheSame: (Any, Any) -> Boolean
    ): ItemCallback {
        return SimpleItemCallback(areItemsTheSame, areContentsTheSame)
    }
    
    /**
     * Create an ItemCallback using equals() for content comparison.
     */
    fun create(areItemsTheSame: (Any, Any) -> Boolean): ItemCallback {
        return SimpleItemCallback(areItemsTheSame) { oldItem, newItem ->
            oldItem == newItem
        }
    }
}

/**
 * Extension function to create an ItemCallback.
 */
inline fun createItemCallback(
    crossinline areItemsTheSame: (Any, Any) -> Boolean,
    crossinline areContentsTheSame: (Any, Any) -> Boolean
): ItemCallback {
    return object : ItemCallback() {
        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            return areItemsTheSame(oldItem, newItem)
        }
        
        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return areContentsTheSame(oldItem, newItem)
        }
    }
}
