package com.multitype.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class AsyncListDifferTest {
    
    @Test
    fun test_create_differ() {
        val callback = object : ItemCallback() {
            override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
                return oldItem == newItem
            }
        }
        
        val differ = AsyncListDifferBuilder.create(callback)
        assertTrue(differ.getItemCount() == 0)
    }
    
    @Test
    fun test_submit_empty_list() {
        val callback = object : ItemCallback() {
            override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean = oldItem == newItem
            override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean = oldItem == newItem
        }
        
        val differ = AsyncListDifferBuilder.create(callback)
        differ.submitList(listOf("a", "b", "c"))
        
        assertEquals(3, differ.getItemCount())
    }
    
    @Test
    fun test_submit_null() {
        val callback = object : ItemCallback() {
            override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean = oldItem == newItem
            override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean = oldItem == newItem
        }
        
        val differ = AsyncListDifferBuilder.create(callback)
        differ.submitList(listOf("a", "b"))
        differ.submitList(null)
        
        assertEquals(0, differ.getItemCount())
    }
    
    @Test
    fun test_get_item() {
        val callback = object : ItemCallback() {
            override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean = oldItem == newItem
            override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean = oldItem == newItem
        }
        
        val differ = AsyncListDifferBuilder.create(callback)
        differ.submitList(listOf("a", "b", "c"))
        
        assertEquals("a", differ.getItem(0))
        assertEquals("b", differ.getItem(1))
        assertEquals("c", differ.getItem(2))
    }
    
    @Test
    fun test_get_current_list() {
        val callback = object : ItemCallback() {
            override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean = oldItem == newItem
            override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean = oldItem == newItem
        }
        
        val differ = AsyncListDifferBuilder.create(callback)
        differ.submitList(listOf("a", "b"))
        
        val list = differ.getCurrentList()
        assertEquals(2, list.size)
        assertEquals("a", list[0])
    }
    
    @Test
    fun test_diff_result() {
        val result = DiffResult(
            added = listOf(2, 3),
            removed = listOf(0),
            unchanged = listOf(1)
        )
        
        assertTrue(result.hasChanges())
        assertEquals(3, result.getChangeCount())
    }
    
    @Test
    fun test_diff_result_no_changes() {
        val result = DiffResult(
            added = emptyList(),
            removed = emptyList(),
            unchanged = listOf(0, 1, 2)
        )
        
        assertFalse(result.hasChanges())
        assertEquals(0, result.getChangeCount())
    }
}
