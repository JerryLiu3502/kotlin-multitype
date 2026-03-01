package com.multitype.core

/**
 * Item transformer for transforming items.
 */
interface ItemTransformer<T, R> {
    fun transform(item: T): R
}

/**
 * Simple ItemTransformer implementation.
 */
class SimpleItemTransformer<T, R>(
    private val transformer: (T) -> R
) : ItemTransformer<T, R> {
    override fun transform(item: T): R = transformer(item)
}

/**
 * Builder for ItemTransformer.
 */
object ItemTransformerBuilder {
    fun <T, R> create(transformer: (T) -> R): ItemTransformer<T, R> {
        return SimpleItemTransformer(transformer)
    }
    
    fun toString(): ItemTransformer<Any, String> {
        return SimpleItemTransformer { it.toString() }
    }
    
    fun <T : Any> toList(): ItemTransformer<T, List<T>> {
        return SimpleItemTransformer { listOf(it) }
    }
}
