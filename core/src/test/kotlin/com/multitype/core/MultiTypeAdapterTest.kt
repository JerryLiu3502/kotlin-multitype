package com.multitype.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFailsWith

class MultiTypeAdapterTest {
    
    @Test
    fun test_adapter_starts_empty() {
        val adapter = MultiTypeAdapter()
        assertEquals(0, adapter.itemCount)
    }
    
    @Test
    fun test_adapter_starts_empty_via_count_method() {
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
        val binder = TestBinder()
        adapter.register(binder)
        // Registration should not throw
        assertTrue(true)
    }
    
    @Test
    fun test_item_view_type() {
        val adapter = MultiTypeAdapter()
        adapter.addItem("string item")
        adapter.register(TestBinder())
        // Should not throw with registered binder
        val viewType = adapter.getItemViewType(0)
        assertEquals(0, viewType)
    }
    
    @Test
    fun test_on_bind_view_holder() {
        val adapter = MultiTypeAdapter()
        adapter.addItem("test string")
        adapter.register(TestBinder())
        
        // Should not throw
        adapter.onBindViewHolder(Unit, 0)
        assertTrue(true)
    }
}

/**
 * Test binder for string items.
 */
class TestBinder : ItemBinder<String> {
    override fun getLayoutId(): Int = 1
    
    override fun getContentType(): Int = 0
    
    override fun getItemClass(): Class<out String> = String::class.java
    
    override fun onBind(binding: Any, item: String, position: Int) {
        // Test implementation
    }
}
