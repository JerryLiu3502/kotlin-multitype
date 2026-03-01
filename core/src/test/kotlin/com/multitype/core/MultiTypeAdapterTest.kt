package com.multitype.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFailsWith
import com.multitype.annotations.ItemViewBinder

class MultiTypeAdapterTest {
    
    @Test
    fun test_adapter_starts_empty() {
        val adapter = MultiTypeAdapter()
        assertEquals(0, adapter.itemCount)
    }
    
    @Test
    fun test_add_single_item() {
        val adapter = MultiTypeAdapter()
        adapter.addItem("test item")
        assertEquals(1, adapter.itemCount)
    }
    
    @Test
    fun test_add_multiple_items() {
        val adapter = MultiTypeAdapter()
        adapter.addItems(listOf("item1", "item2", "item3"))
        assertEquals(3, adapter.itemCount)
    }
    
    @Test
    fun test_clear_items() {
        val adapter = MultiTypeAdapter()
        adapter.addItems(listOf("item1", "item2"))
        adapter.clearItems()
        assertEquals(0, adapter.itemCount)
    }
    
    @Test
    fun test_get_item() {
        val adapter = MultiTypeAdapter()
        adapter.addItem("test")
        assertEquals("test", adapter.getItem(0))
    }
    
    @Test
    fun test_get_items() {
        val adapter = MultiTypeAdapter()
        val items = listOf("a", "b", "c")
        adapter.addItems(items)
        assertEquals(items, adapter.getItems())
    }
    
    @Test
    fun test_register_binder() {
        val adapter = MultiTypeAdapter()
        val binder = StringBinder()
        adapter.register(binder)
        // Registration should not throw
        assertTrue(true)
    }
    
    @Test
    fun test_item_view_type() {
        val adapter = MultiTypeAdapter()
        adapter.addItem("string item")
        adapter.register(StringBinder())
        // Should not throw with registered binder
        val viewType = adapter.getItemViewType(0)
        assertEquals(0, viewType)
    }
    
    @Test
    fun test_on_bind_view_holder() {
        val adapter = MultiTypeAdapter()
        adapter.addItem("test string")
        adapter.register(StringBinder())
        
        // Should not throw
        adapter.onBindViewHolder(Unit, 0)
        assertTrue(true)
    }
}

class TypePoolTest {
    
    @Test
    fun test_register_binder() {
        val pool = TypePool()
        pool.register(StringBinder())
        assertEquals(1, pool.getTypeCount())
    }
    
    @Test
    fun test_register_multiple_binders() {
        val pool = TypePool()
        pool.register(StringBinder())
        pool.register(IntBinder())
        assertEquals(2, pool.getTypeCount())
    }
    
    @Test
    fun test_get_binder_by_class() {
        val pool = TypePool()
        val binder = StringBinder()
        pool.register(binder)
        
        val found = pool.getBinder(String::class.java)
        assertTrue(found != null)
    }
    
    @Test
    fun test_get_type_by_class() {
        val pool = TypePool()
        val binder = StringBinder()
        pool.register(binder)
        
        val type = pool.getType(String::class.java)
        assertTrue(type != null)
    }
    
    @Test
    fun test_clear() {
        val pool = TypePool()
        pool.register(StringBinder())
        pool.register(IntBinder())
        
        pool.clear()
        assertEquals(0, pool.getTypeCount())
    }
}

/**
 * Test binder for string items.
 */
class StringBinder : ItemBinder() {
    override fun getItemClass(): Class<*> = String::class.java
    
    override fun bind(holder: Any, item: Any, position: Int) {
        // Test implementation
    }
}

/**
 * Test binder for integer items.
 */
class IntBinder : ItemBinder() {
    override fun getItemClass(): Class<*> = Int::class.java
    
    override fun bind(holder: Any, item: Any, position: Int) {
        // Test implementation
    }
}

/**
 * Test data class.
 */
data class TestData(val name: String, val value: Int)

/**
 * Test binder for TestData items.
 */
class TestDataBinder : ItemBinder() {
    override fun getItemClass(): Class<*> = TestData::class.java
    
    override fun bind(holder: Any, item: Any, position: Int) {
        // Test implementation
    }
}
