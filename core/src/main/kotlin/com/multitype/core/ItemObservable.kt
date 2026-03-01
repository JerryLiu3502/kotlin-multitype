package com.multitype.core

/**
 * Item observer for observing item changes.
 */
interface ItemObserver {
    fun onChanged(item: Any)
    fun onAdded(item: Any, position: Int)
    fun onRemoved(item: Any, position: Int)
    fun onMoved(fromPosition: Int, toPosition: Int)
}

/**
 * Simple ItemObserver implementation.
 */
open class SimpleItemObserver : ItemObserver {
    override fun onChanged(item: Any) {}
    override fun onAdded(item: Any, position: Int) {}
    override fun onRemoved(item: Any, position: Int) {}
    override fun onMoved(fromPosition: Int, toPosition: Int) {}
}

/**
 * Observable for items.
 */
class ItemObservable {
    private val observers = mutableListOf<ItemObserver>()
    
    fun registerObserver(observer: ItemObserver) {
        observers.add(observer)
    }
    
    fun unregisterObserver(observer: ItemObserver) {
        observers.remove(observer)
    }
    
    fun notifyItemChanged(item: Any) {
        observers.forEach { it.onChanged(item) }
    }
    
    fun notifyItemAdded(item: Any, position: Int) {
        observers.forEach { it.onAdded(item, position) }
    }
    
    fun notifyItemRemoved(item: Any, position: Int) {
        observers.forEach { it.onRemoved(item, position) }
    }
    
    fun notifyItemMoved(fromPosition: Int, toPosition: Int) {
        observers.forEach { it.onMoved(fromPosition, toPosition) }
    }
    
    fun getObserverCount(): Int = observers.size
}
