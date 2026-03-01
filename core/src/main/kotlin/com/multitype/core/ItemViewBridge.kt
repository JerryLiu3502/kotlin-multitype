package com.multitype.core

/**
 * Interface for view binding in ItemBinder.
 * 
 * This is similar to Drakeet's ViewBinder.
 */
interface ItemViewBridge<in V> {
    
    /**
     * Bind the view with the item.
     * 
     * @param view The view to bind
     * @param item The item data
     * @param position The position in the adapter
     */
    fun bindView(view: V, item: Any, position: Int)
    
    /**
     * Called when the view is recycled.
     * 
     * @param view The view that was recycled
     */
    fun onRecycle(view: V) {}
}

/**
 * Simple implementation of ItemViewBridge using a lambda.
 */
class SimpleItemViewBridge<V>(
    private val bind: (V, Any, Int) -> Unit
) : ItemViewBridge<V> {
    
    override fun bindView(view: V, item: Any, position: Int) {
        bind(view, item, position)
    }
}

/**
 * Helper class for creating view binders.
 */
object ViewBinder {
    
    /**
     * Create an ItemViewBridge with a bind function.
     */
    fun <V> create(
        bind: (V, Any, Int) -> Unit
    ): ItemViewBridge<V> {
        return SimpleItemViewBridge(bind)
    }
    
    /**
     * Create an ItemViewBridge with bind and recycle functions.
     */
    fun <V> create(
        bind: (V, Any, Int) -> Unit,
        recycle: (V) -> Unit
    ): ItemViewBridge<V> {
        return object : ItemViewBridge<V> {
            override fun bindView(view: V, item: Any, position: Int) {
                bind(view, item, position)
            }
            
            override fun onRecycle(view: V) {
                recycle(view)
            }
        }
    }
}
