package com.multitype.core

/**
 * Wrapper for item with metadata.
 * 
 * This is useful for attaching extra data to items.
 */
data class ItemWrapper(
    val item: Any,
    val extras: Map<String, Any> = emptyMap()
) {
    /**
     * Get an extra value by key.
     */
    @Suppress("UNCHECKED_CAST")
    fun <T> getExtra(key: String): T? {
        return extras[key] as? T
    }
    
    /**
     * Get an extra value with default.
     */
    fun <T> getExtraOrDefault(key: String, default: T): T {
        return extras[key] as? T ?: default
    }
    
    /**
     * Check if an extra exists.
     */
    fun hasExtra(key: String): Boolean {
        return extras.containsKey(key)
    }
}

/**
 * Builder for ItemWrapper.
 */
class ItemWrapperBuilder(private val item: Any) {
    private val extras = mutableMapOf<String, Any>()
    
    /**
     * Add an extra key-value pair.
     */
    fun putExtra(key: String, value: Any): ItemWrapperBuilder {
        extras[key] = value
        return this
    }
    
    /**
     * Build the ItemWrapper.
     */
    fun build(): ItemWrapper {
        return ItemWrapper(item, extras.toMap())
    }
}

/**
 * Extension function to wrap an item.
 */
fun Any.withExtras(vararg extras: Pair<String, Any>): ItemWrapper {
    return ItemWrapper(this, extras.toMap())
}

/**
 * Extension function to wrap an item using builder.
 */
fun Any.wrap(): ItemWrapperBuilder {
    return ItemWrapperBuilder(this)
}
