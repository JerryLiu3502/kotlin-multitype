package com.multitype.core

/**
 * Simple adapter for supporting multiple item types.
 */
class MultiTypeAdapter {
    
    private val classToBinder = mutableMapOf<Class<*>, ItemBinder>()
    private val typeToBinder = mutableMapOf<Int, ItemBinder>()
    private val items = mutableListOf<Any>()
    
    /**
     * Register a binder for a specific content type.
     */
    fun register(binder: ItemBinder) {
        classToBinder[binder.getItemClass()] = binder
        typeToBinder[binder.getContentType()] = binder
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
    val itemCount: Int get() = items.size
    
    /**
     * Get view type for position.
     */
    fun getItemViewType(position: Int): Int {
        val item = items[position]
        
        // First try to find by class
        val binder = classToBinder[item::class.java]
            ?: typeToBinder.entries.find { it.value.canHandle(item) }?.value
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
        
        binder.bind(holder, item, position)
    }
}
