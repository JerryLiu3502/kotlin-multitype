package com.multitype.core

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

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
     * Called when the view is created. Bind the data to the view here.
     */
    abstract fun onBind(binding: Any, item: T, position: Int)
    
    /**
     * Called when the view is recycled. Clean up any resources here.
     */
    open fun onRecycle(binding: Any) {}
}

/**
 * Adapter for supporting multiple item types in a RecyclerView.
 * 
 * This is similar to GitHub's MultiTypeAdapter library.
 */
class MultiTypeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    
    private val binders = mutableMapOf<Int, ItemBinder<*>>()
    private val items = mutableListOf<Any>()
    
    /**
     * Register a binder for a specific content type.
     */
    fun <T> register(binder: ItemBinder<T>) {
        @Suppress("UNCHECKED_CAST")
        binders[binder.getContentType()] = binder as ItemBinder<*>
    }
    
    /**
     * Add an item to the adapter.
     */
    fun addItem(item: Any) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }
    
    /**
     * Add multiple items to the adapter.
     */
    fun addItems(newItems: List<Any>) {
        val start = items.size
        items.addAll(newItems)
        notifyItemRangeInserted(start, newItems.size)
    }
    
    /**
     * Clear all items.
     */
    fun clearItems() {
        items.clear()
        notifyDataSetChanged()
    }
    
    /**
     * Get item at position.
     */
    fun getItem(position: Int): Any = items[position]
    
    /**
     * Get all items.
     */
    fun getItems(): List<Any> = items.toList()
    
    override fun getItemViewType(position: Int): Int {
        val item = items[position]
        for ((type, binder) in binders) {
            if (binder.getContentType().isInstance(item)) {
                return type
            }
        }
        throw IllegalArgumentException("No binder found for item: ${item::class.java}")
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binder = binders[viewType] 
            ?: throw IllegalArgumentException("No binder found for view type: $viewType")
        
        // In a real implementation, we would inflate the layout here
        // For now, we return a simple placeholder
        return object : RecyclerView.ViewHolder(View(parent.context)) {}
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        val viewType = getItemViewType(position)
        val binder = binders[viewType] ?: return
        
        // In a real implementation, we would bind the data here
        // For now, this is a placeholder
    }
    
    override fun getItemCount(): Int = items.size
    
    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        super.onViewRecycled(holder)
        val viewType = holder.itemViewType
        val binder = binders[viewType]
        // In a real implementation, we would call onRecycle here
    }
}
