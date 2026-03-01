package com.multitype.core

/**
 * Interface for items that can be selected.
 */
interface Selectable {
    
    /**
     * Check if the item is selected.
     */
    fun isSelected(): Boolean
    
    /**
     * Set the selection state.
     */
    fun setSelected(selected: Boolean)
    
    /**
     * Toggle the selection state.
     */
    fun toggleSelected()
}

/**
 * Simple implementation of Selectable.
 */
abstract class BaseSelectableItem : Selectable {
    
    private var selected: Boolean = false
    
    override fun isSelected(): Boolean = selected
    
    override fun setSelected(selected: Boolean) {
        this.selected = selected
    }
    
    override fun toggleSelected() {
        selected = !selected
    }
}

/**
 * Extension function to get all selected items from a list.
 */
fun <T : Selectable> List<T>.getSelectedItems(): List<T> {
    return filter { it.isSelected() }
}

/**
 * Extension function to get all unselected items from a list.
 */
fun <T : Selectable> List<T>.getUnselectedItems(): List<T> {
    return filter { !it.isSelected() }
}

/**
 * Extension function to clear all selections.
 */
fun <T : Selectable> List<T>.clearAllSelections() {
    forEach { it.setSelected(false) }
}

/**
 * Extension function to select all items.
 */
fun <T : Selectable> List<T>.selectAll() {
    forEach { it.setSelected(true) }
}

/**
 * Extension function to toggle all items.
 */
fun <T : Selectable> List<T>.toggleAll() {
    forEach { it.toggleSelected() }
}
