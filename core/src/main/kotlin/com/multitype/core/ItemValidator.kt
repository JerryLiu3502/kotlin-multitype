package com.multitype.core

/**
 * Item validator for validating items.
 */
interface ItemValidator {
    fun isValid(item: Any): Boolean
    fun getErrorMessage(item: Any): String?
}

/**
 * Simple ItemValidator implementation.
 */
class SimpleItemValidator(
    private val validator: (Any) -> Boolean,
    private val errorMessage: (Any) -> String = { "Invalid item" }
) : ItemValidator {
    override fun isValid(item: Any): Boolean = validator(item)
    override fun getErrorMessage(item: Any): String? = if (!validator(item)) errorMessage(item) else null
}

/**
 * Builder for ItemValidator.
 */
object ItemValidatorBuilder {
    fun create(validator: (Any) -> Boolean, errorMessage: (Any) -> String = { "Invalid item" }): ItemValidator {
        return SimpleItemValidator(validator, errorMessage)
    }
    
    fun createNotNull(): ItemValidator {
        return SimpleItemValidator({ it != null }, { "Item cannot be null" })
    }
    
    fun createNotBlank(): ItemValidator {
        return SimpleItemValidator({ 
            when (it) {
                is String -> it.isNotBlank()
                is Collection<*> -> it.isNotEmpty()
                else -> true
            }
        }, { "Item cannot be blank" })
    }
}
