package com.multitype.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ItemObservableTest {
    
    @Test
    fun test_register_observer() {
        val observable = ItemObservable()
        val observer = object : ItemObserver {}
        
        observable.registerObserver(observer)
        
        assertEquals(1, observable.getObserverCount())
    }
    
    @Test
    fun test_unregister_observer() {
        val observable = ItemObservable()
        val observer = object : ItemObserver {}
        observable.registerObserver(observer)
        
        observable.unregisterObserver(observer)
        
        assertEquals(0, observable.getObserverCount())
    }
    
    @Test
    fun test_notify_changed() {
        var changed = false
        val observable = ItemObservable()
        val observer = object : ItemObserver {
            override fun onChanged(item: Any) { changed = true }
        }
        observable.registerObserver(observer)
        
        observable.notifyItemChanged("test")
        
        assertTrue(changed)
    }
    
    @Test
    fun test_notify_added() {
        var added = false
        val observable = ItemObservable()
        val observer = object : ItemObserver {
            override fun onAdded(item: Any, position: Int) { added = true }
        }
        observable.registerObserver(observer)
        
        observable.notifyItemAdded("test", 0)
        
        assertTrue(added)
    }
    
    @Test
    fun test_notify_removed() {
        var removed = false
        val observable = ItemObservable()
        val observer = object : ItemObserver {
            override fun onRemoved(item: Any, position: Int) { removed = true }
        }
        observable.registerObserver(observer)
        
        observable.notifyItemRemoved("test", 0)
        
        assertTrue(removed)
    }
}
