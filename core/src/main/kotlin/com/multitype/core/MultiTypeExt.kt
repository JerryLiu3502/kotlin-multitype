package com.multitype.core

/**
 * Extension functions for MultiTypeRecyclerViewAdapter.
 */

/**
 * Add multiple items at once.
 */
fun MultiTypeRecyclerViewAdapter.setItemsAndNotify(newItems: List<Any>) {
    setItems(newItems)
}

/**
 * Add item and notify.
 */
fun MultiTypeRecyclerViewAdapter.addItemAndNotify(item: Any) {
    addItem(item)
}

/**
 * Add multiple items and notify.
 */
fun MultiTypeRecyclerViewAdapter.addItemsAndNotify(items: List<Any>) {
    addItems(items)
}

/**
 * Clear all items and notify.
 */
fun MultiTypeRecyclerViewAdapter.clearItemsAndNotify() {
    clearItems()
}

/**
 * Extension function to get item at position with type safety.
 */
@Suppress("UNCHECKED_CAST")
fun <T> MultiTypeRecyclerViewAdapter.getItemAt(position: Int): T {
    return getItem(position) as T
}

/**
 * Extension function to get items with type safety.
 */
@Suppress("UNCHECKED_CAST")
fun <T> MultiTypeRecyclerViewAdapter.getItemsOfType(clazz: Class<T>): List<T> {
    return getItems().filterIsInstance(clazz)
}

/**
 * Get the position of an item in the adapter.
 */
fun MultiTypeRecyclerViewAdapter.indexOf(item: Any): Int {
    return getItems().indexOf(item)
}

/**
 * Check if adapter contains an item.
 */
fun MultiTypeRecyclerViewAdapter.contains(item: Any): Boolean {
    return getItems().contains(item)
}

/**
 * Check if adapter is empty.
 */
fun MultiTypeRecyclerViewAdapter.isEmpty(): Boolean {
    return getItemCount() == 0
}

/**
 * Check if adapter is not empty.
 */
fun MultiTypeRecyclerViewAdapter.isNotEmpty(): Boolean {
    return getItemCount() > 0
}
