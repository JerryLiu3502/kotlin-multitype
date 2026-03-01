package com.multitype.core

/**
 * Item selector for selecting items.
 */
class ItemSelector {
    private val selectedItems = mutableSetOf<Any>()
    
    /**
     * Select an item.
     */
    fun select(item: Any) {
        selectedItems.add(item)
    }
    
    /**
     * Deselect an item.
     */
    fun deselect(item: Any) {
        selectedItems.remove(item)
    }
    
    /**
     * Toggle selection.
     */
    fun toggle(item: Any): Boolean {
        return if (selectedItems.contains(item)) {
            selectedItems.remove(item)
            false
        } else {
            selectedItems.add(item)
            true
        }
    }
    
    /**
     * Check if item is selected.
     */
    fun isSelected(item: Any): Boolean = selectedItems.contains(item)
    
    /**
     * Get all selected items.
     */
    fun getSelectedItems(): Set<Any> = selectedItems.toSet()
    
    /**
     * Get selected count.
     */
    fun getSelectedCount(): Int = selectedItems.size
    
    /**
     * Clear all selections.
     */
    fun clear() {
        selectedItems.clear()
    }
    
    /**
     * Select all items.
     */
    fun selectAll(items: List<Any>) {
        selectedItems.addAll(items)
    }
    
    /**
     * Check if has selections.
     */
    fun hasSelection(): Boolean = selectedItems.isNotEmpty()
}

/**
 * Builder for ItemSelector.
 */
object ItemSelectorBuilder {
    fun create(): ItemSelector = ItemSelector()
}
