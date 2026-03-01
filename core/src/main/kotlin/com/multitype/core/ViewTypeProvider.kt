package com.multitype.core

/**
 * Provider for view type based on item.
 */
interface ViewTypeProvider {
    
    /**
     * Get view type for item.
     */
    fun getViewType(item: Any): Int
}

/**
 * Simple implementation of ViewTypeProvider.
 */
class SimpleViewTypeProvider(
    private val provider: (Any) -> Int
) : ViewTypeProvider {
    
    override fun getViewType(item: Any): Int {
        return provider(item)
    }
}

/**
 * Builder for ViewTypeProvider.
 */
object ViewTypeProviderBuilder {
    
    /**
     * Create by class.
     */
    fun createByClass(): ViewTypeProvider {
        return SimpleViewTypeProvider { item ->
            item::class.java.hashCode()
        }
    }
    
    /**
     * Create by custom function.
     */
    fun create(provider: (Any) -> Int): ViewTypeProvider {
        return SimpleViewTypeProvider(provider)
    }
}
