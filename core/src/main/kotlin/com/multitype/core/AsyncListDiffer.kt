package com.multitype.core

/**
 * Helper for computing diffs between lists asynchronously.
 * 
 * This is similar to Drakeet's AsyncListDiffer.
 */
class AsyncListDiffer(
    private val callback: ItemCallback
) {
    private val results = mutableListOf<Any>()
    
    /**
     * Get current list.
     */
    fun getCurrentList(): List<Any> = results.toList()
    
    /**
     * Submit a new list to process.
     */
    fun submitList(newList: List<Any>?) {
        if (newList == null) {
            results.clear()
            return
        }
        
        // Calculate diff
        val diffResult = calculateDiff(results.toList(), newList)
        
        // Apply changes
        results.clear()
        results.addAll(newList)
        
        // Notify changes (in a real implementation, this would call adapter methods)
    }
    
    /**
     * Calculate diff between old and new lists.
     */
    private fun calculateDiff(oldList: List<Any>, newList: List<Any>): DiffResult {
        val added = mutableListOf<Int>()
        val removed = mutableListOf<Int>()
        val unchanged = mutableListOf<Int>()
        
        // Simple diff calculation
        for (i in newList.indices) {
            val item = newList[i]
            if (oldList.contains(item)) {
                unchanged.add(i)
            } else {
                added.add(i)
            }
        }
        
        for (i in oldList.indices) {
            val item = oldList[i]
            if (!newList.contains(item)) {
                removed.add(i)
            }
        }
        
        return DiffResult(added, removed, unchanged)
    }
    
    /**
     * Get the number of items.
     */
    fun getItemCount(): Int = results.size
    
    /**
     * Get item at position.
     */
    fun getItem(position: Int): Any = results[position]
}

/**
 * Result of diff calculation.
 */
data class DiffResult(
    val added: List<Int>,
    val removed: List<Int>,
    val unchanged: List<Int>
) {
    /**
     * Check if there are any changes.
     */
    fun hasChanges(): Boolean = added.isNotEmpty() || removed.isNotEmpty()
    
    /**
     * Get the number of changes.
     */
    fun getChangeCount(): Int = added.size + removed.size
}

/**
 * Builder for AsyncListDiffer.
 */
object AsyncListDifferBuilder {
    
    /**
     * Create an AsyncListDiffer.
     */
    fun create(callback: ItemCallback): AsyncListDiffer {
        return AsyncListDiffer(callback)
    }
}
