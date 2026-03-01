package com.multitype.core

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class SelectableItemTest {
    
    @Test
    fun test_selectable_default_not_selected() {
        val item = object : BaseSelectableItem() {
            override fun toString() = "TestItem"
        }
        
        assertFalse(item.isSelected())
    }
    
    @Test
    fun test_set_selected() {
        val item = object : BaseSelectableItem() {
            override fun toString() = "TestItem"
        }
        
        item.setSelected(true)
        assertTrue(item.isSelected())
        
        item.setSelected(false)
        assertFalse(item.isSelected())
    }
    
    @Test
    fun test_toggle_selected() {
        val item = object : BaseSelectableItem() {
            override fun toString() = "TestItem"
        }
        
        assertFalse(item.isSelected())
        item.toggleSelected()
        assertTrue(item.isSelected())
        item.toggleSelected()
        assertFalse(item.isSelected())
    }
    
    @Test
    fun test_get_selected_items() {
        val items = listOf(
            createSelectable(false),
            createSelectable(true),
            createSelectable(false),
            createSelectable(true)
        )
        
        val selected = items.getSelectedItems()
        assertEquals(2, selected.size)
    }
    
    @Test
    fun test_get_unselected_items() {
        val items = listOf(
            createSelectable(false),
            createSelectable(true),
            createSelectable(false),
            createSelectable(true)
        )
        
        val unselected = items.getUnselectedItems()
        assertEquals(2, unselected.size)
    }
    
    @Test
    fun test_clear_all_selections() {
        val items = listOf(
            createSelectable(true),
            createSelectable(true),
            createSelectable(false)
        )
        
        items.clearAllSelections()
        
        assertTrue(items.all { !it.isSelected() })
    }
    
    @Test
    fun test_select_all() {
        val items = listOf(
            createSelectable(false),
            createSelectable(false),
            createSelectable(false)
        )
        
        items.selectAll()
        
        assertTrue(items.all { it.isSelected() })
    }
    
    @Test
    fun test_toggle_all() {
        val items = listOf(
            createSelectable(true),
            createSelectable(false),
            createSelectable(true)
        )
        
        items.toggleAll()
        
        assertTrue(items[0].isSelected() == false)
        assertTrue(items[1].isSelected() == true)
        assertTrue(items[2].isSelected() == false)
    }
    
    private fun createSelectable(initialSelected: Boolean): Selectable {
        return object : BaseSelectableItem() {
            init {
                setSelected(initialSelected)
            }
            override fun toString() = "SelectableItem"
        }
    }
}
