package com.multitype.core

/**
 * Loader for loading items.
 */
interface ItemLoader {
    fun load(onSuccess: (List<Any>) -> Unit, onError: (Throwable) -> Unit)
    fun cancel()
}

/**
 * Simple ItemLoader implementation.
 */
class SimpleItemLoader(
    private val loader: (onSuccess: (List<Any>) -> Unit, onError: (Throwable) -> Unit) -> Unit
) : ItemLoader {
    override fun load(onSuccess: (List<Any>) -> Unit, onError: (Throwable) -> Unit) {
        loader(onSuccess, onError)
    }
    
    override fun cancel() {
        // Override to implement cancellation
    }
}

/**
 * Builder for ItemLoader.
 */
object ItemLoaderBuilder {
    fun create(loader: (onSuccess: (List<Any>) -> Unit, onError: (Throwable) -> Unit) -> Unit): ItemLoader {
        return SimpleItemLoader(loader)
    }
    
    fun createFromList(items: List<Any>): ItemLoader {
        return SimpleItemLoader { onSuccess, _ -> onSuccess(items) }
    }
    
    fun createEmpty(): ItemLoader {
        return SimpleItemLoader { onSuccess, _ -> onSuccess(emptyList()) }
    }
}
