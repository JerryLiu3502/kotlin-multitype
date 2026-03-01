package com.multitype.core

/**
 * Base class for multi-type item binders.
 * Each binder handles a specific type of content.
 */
abstract class ItemBinder<T> {
    
    /**
     * Returns the layout resource ID for this binder.
     */
    abstract fun getLayoutId(): Int
    
    /**
     * Returns the content type identifier for this binder.
     */
    abstract fun getContentType(): Int
    
    /**
     * Returns the class type this binder handles.
     */
    abstract fun getItemClass(): Class<out T>
    
    /**
     * Called when the view is created. Bind the data to the view here.
     */
    abstract fun onBind(binding: Any, item: T, position: Int)
    
    /**
     * Called when the view is recycled. Clean up any resources here.
     */
    open fun onRecycle(binding: Any) {}
}

/**
 * Simple data class for items.
 */
data class Item(val content: String, val type: Int)

/**
 * Adapter for supporting multiple item types.
 * 
 * This is similar to GitHub's MultiTypeAdapter library.
 */
class MultiTypeAdapter {
    
    private val classToBinder = mutableMapOf<Class<*>, ItemBinder<*>>()
    private val typeToBinder = mutableMapOf<Int, ItemBinder<*>>()
    private val items = mutableListOf<Any>()
    
    /**
     * Register a binder for a specific content type.
     */
    fun <T> register(binder: ItemBinder<T>) {
        @Suppress("UNCHECKED_CAST")
        classToBinder[binder.getItemClass()] = binder
        typeToBinder[binder.getContentType()] = binder as ItemBinder<*>
    }
    
    /**
     * Add an item to the adapter.
     */
    fun addItem(item: Any) {
        items.add(item)
    }
    
    /**
     * Add multiple items to the adapter.
     */
    fun addItems(newItems: List<Any>) {
        items.addAll(newItems)
    }
    
    /**
     * Clear all items.
     */
    fun clearItems() {
        items.clear()
    }
    
    /**
     * Get item at position.
     */
    fun getItem(position: Int): Any = items[position]
    
    /**
     * Get all items.
     */
    fun getItems(): List<Any> = items.toList()
    
    /**
     * Get item count.
     */
    fun getItemCount(): Int = items.size
    
    /**
     * Get view type for position.
     */
    fun getItemViewType(position: Int): Int {
        val item = items[position]
        
        // First try to find by class
        val binder = classToBinder[item::class.java]
            ?: typeToBinder.entries.find { it.value.getItemClass().isInstance(item) }?.value
            ?: throw IllegalArgumentException("No binder found for item: ${item::class.java}")
        
        return binder.getContentType()
    }
    
    /**
     * Bind item at position.
     */
    fun onBindViewHolder(holder: Any, position: Int) {
        val item = items[position]
        val viewType = getItemViewType(position)
        val binder = typeToBinder[viewType] ?: return
        
        @Suppress("UNCHECKED_CAST")
        (binder as ItemBinder<Any>).onBind(holder, item, position)
    }
}
