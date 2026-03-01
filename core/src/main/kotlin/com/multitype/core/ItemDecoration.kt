package com.multitype.core

/**
 * Base class for item decorations.
 * 
 * This is similar to RecyclerView.ItemDecoration.
 */
abstract class ItemDecoration {
    
    /**
     * Called to draw decorations around the item.
     */
    abstract fun onDraw(canvas: Any, parent: Any, state: Any)
    
    /**
     * Called to draw decorations around the item.
     */
    open fun onDrawOver(canvas: Any, parent: Any, state: Any) {}
    
    /**
     * Get the item offsets.
     */
    open fun getItemOffsets(
        outRect: Any,
        position: Int,
        item: Any,
        parentSize: Int
    ) {}
}

/**
 * Simple implementation of ItemDecoration.
 */
abstract class SimpleItemDecoration : ItemDecoration() {
    
    override fun onDraw(canvas: Any, parent: Any, state: Any) {
        // Override to implement
    }
}

/**
 * Builder for creating ItemDecoration.
 */
object ItemDecorationBuilder {
    
    /**
     * Create a spacing decoration.
     */
    fun createSpacing(spacing: Int): ItemDecoration {
        return object : SimpleItemDecoration() {
            override fun getItemOffsets(
                outRect: Any,
                position: Int,
                item: Any,
                parentSize: Int
            ) {
                // Set spacing - this would normally use outRect.set() in Android
            }
        }
    }
    
    /**
     * Create a divider decoration.
     */
    fun createDivider(height: Int, color: Int): ItemDecoration {
        return object : SimpleItemDecoration() {
            override fun onDraw(canvas: Any, parent: Any, state: Any) {
                // Draw divider - this would normally use canvas.drawRect() in Android
            }
        }
    }
}
