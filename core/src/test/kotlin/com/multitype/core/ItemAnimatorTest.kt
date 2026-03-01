package com.multitype.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ItemAnimatorTest {
    
    @Test
    fun test_simple_animator() {
        val animator = SimpleItemAnimator()
        // Just ensure it can be created
        assertTrue(animator != null)
    }
    
    @Test
    fun test_builder_with_all_callbacks() {
        var added = false
        var removed = false
        var moved = false
        var changed = false
        
        val animator = ItemAnimatorBuilder.create(
            onAdded = { _, _ -> added = true },
            onRemoved = { _, _ -> removed = true },
            onMoved = { _, _ -> moved = true },
            onChanged = { _, _, _ -> changed = true }
        )
        
        animator.onItemAdded(0, "item")
        animator.onItemRemoved(0, "item")
        animator.onItemMoved(0, 1)
        animator.onItemChanged(0, "old", "new")
        
        assertTrue(added)
        assertTrue(removed)
        assertTrue(moved)
        assertTrue(changed)
    }
    
    @Test
    fun test_builder_with_partial_callbacks() {
        var added = false
        
        val animator = ItemAnimatorBuilder.create(
            onAdded = { _, _ -> added = true }
        )
        
        animator.onItemAdded(0, "item")
        animator.onItemRemoved(0, "item") // Should not throw
        
        assertTrue(added)
    }
    
    @Test
    fun test_builder_with_no_callbacks() {
        val animator = ItemAnimatorBuilder.create()
        
        // Should not throw
        animator.onItemAdded(0, "item")
        animator.onItemRemoved(0, "item")
        animator.onItemMoved(0, 1)
        animator.onItemChanged(0, "old", "new")
    }
}
