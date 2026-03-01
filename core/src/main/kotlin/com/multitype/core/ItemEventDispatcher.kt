package com.multitype.core

/**
 * Event dispatcher for handling item events.
 * 
 * This is useful for handling click, long click, and other events.
 */
class ItemEventDispatcher {
    
    private val clickListeners = mutableMapOf<Int, (Any, Int) -> Unit>()
    private val longClickListeners = mutableMapOf<Int, (Any, Int) -> Unit>()
    private val itemClickListeners = mutableListOf<(Any, Int) -> Unit>()
    private val itemLongClickListeners = mutableListOf<(Any, Int) -> Unit>()
    
    /**
     * Register a click listener for a specific view type.
     */
    fun addClickListener(viewType: Int, listener: (Any, Int) -> Unit) {
        clickListeners[viewType] = listener
    }
    
    /**
     * Register a long click listener for a specific view type.
     */
    fun addLongClickListener(viewType: Int, listener: (Any, Int) -> Unit) {
        longClickListeners[viewType] = listener
    }
    
    /**
     * Register a global click listener for all view types.
     */
    fun addGlobalClickListener(listener: (Any, Int) -> Unit) {
        itemClickListeners.add(listener)
    }
    
    /**
     * Register a global long click listener for all view types.
     */
    fun addGlobalLongClickListener(listener: (Any, Int) -> Unit) {
        itemLongClickListeners.add(listener)
    }
    
    /**
     * Dispatch click event.
     */
    fun dispatchClick(item: Any, position: Int, viewType: Int) {
        clickListeners[viewType]?.invoke(item, position)
        itemClickListeners.forEach { it.invoke(item, position) }
    }
    
    /**
     * Dispatch long click event.
     */
    fun dispatchLongClick(item: Any, position: Int, viewType: Int): Boolean {
        longClickListeners[viewType]?.invoke(item, position)
        itemLongClickListeners.forEach { it.invoke(item, position) }
        return true
    }
    
    /**
     * Clear all listeners.
     */
    fun clear() {
        clickListeners.clear()
        longClickListeners.clear()
        itemClickListeners.clear()
        itemLongClickListeners.clear()
    }
}

/**
 * Builder for ItemEventDispatcher.
 */
object ItemEventDispatcherBuilder {
    
    /**
     * Create an ItemEventDispatcher.
     */
    fun create(): ItemEventDispatcher {
        return ItemEventDispatcher()
    }
    
    /**
     * Create an ItemEventDispatcher with click listener.
     */
    fun create(onClick: (Any, Int) -> Unit): ItemEventDispatcher {
        val dispatcher = ItemEventDispatcher()
        dispatcher.addGlobalClickListener(onClick)
        return dispatcher
    }
    
    /**
     * Create an ItemEventDispatcher with click and long click listeners.
     */
    fun create(
        onClick: (Any, Int) -> Unit,
        onLongClick: (Any, Int) -> Unit
    ): ItemEventDispatcher {
        val dispatcher = ItemEventDispatcher()
        dispatcher.addGlobalClickListener(onClick)
        dispatcher.addGlobalLongClickListener(onLongClick)
        return dispatcher
    }
}
