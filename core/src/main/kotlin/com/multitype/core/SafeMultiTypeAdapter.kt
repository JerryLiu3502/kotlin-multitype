package com.multitype.core

/**
 * Safe adapter that provides type-safe operations.
 * 
 * This is similar to Drakeet's SafeAdapter.
 */
class SafeMultiTypeAdapter {
    
    private val adapter = MultiTypeRecyclerViewAdapter()
    
    /**
     * Register a type binder with a specific content type.
     */
    fun <T> register(clazz: Class<T>, binder: ItemBinder): SafeMultiTypeAdapter {
        adapter.register(binder)
        return this
    }
    
    /**
     * Register a type binder with auto-generated content type.
     */
    fun <T> register(clazz: Class<T>, binder: ItemBinder, type: Int): SafeMultiTypeAdapter {
        adapter.register(type, binder)
        return this
    }
    
    /**
     * Add an item to the adapter.
     */
    fun addItem(item: Any): SafeMultiTypeAdapter {
        adapter.addItem(item)
        return this
    }
    
    /**
     * Add multiple items.
     */
    fun addItems(items: List<Any>): SafeMultiTypeAdapter {
        adapter.addItems(items)
        return this
    }
    
    /**
     * Set items (replaces all).
     */
    fun setItems(items: List<Any>): SafeMultiTypeAdapter {
        adapter.setItems(items)
        return this
    }
    
    /**
     * Get the underlying adapter.
     */
    fun getAdapter(): MultiTypeRecyclerViewAdapter = adapter
    
    /**
     * Get item count.
     */
    fun getItemCount(): Int = adapter.getItemCount()
    
    /**
     * Get item at position.
     */
    fun getItem(position: Int): Any = adapter.getItem(position)
    
    /**
     * Get all items.
     */
    fun getItems(): List<Any> = adapter.getItems()
    
    /**
     * Get item view type.
     */
    fun getItemViewType(position: Int): Int = adapter.getItemViewType(position)
    
    /**
     * Bind view holder.
     */
    fun onBindViewHolder(holder: Any, position: Int) {
        adapter.onBindViewHolder(holder, position)
    }
}

/**
 * Builder for SafeMultiTypeAdapter.
 */
object SafeMultiTypeAdapterBuilder {
    
    /**
     * Create a new SafeMultiTypeAdapter.
     */
    fun create(): SafeMultiTypeAdapter {
        return SafeMultiTypeAdapter()
    }
    
    /**
     * Create and configure a SafeMultiTypeAdapter.
     */
    fun create(configure: SafeMultiTypeAdapter.() -> Unit): SafeMultiTypeAdapter {
        val adapter = SafeMultiTypeAdapter()
        adapter.configure()
        return adapter
    }
}

/**
 * Extension function to create a SafeMultiTypeAdapter.
 */
fun safeMultiTypeAdapter(configure: SafeMultiTypeAdapter.() -> Unit): SafeMultiTypeAdapter {
    return SafeMultiTypeAdapterBuilder.create(configure)
}
