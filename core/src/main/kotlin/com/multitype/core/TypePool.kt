package com.multitype.core

/**
 * TypePool manages the registration and lookup of ItemBinders.
 * 
 * This is similar to Drakeet's TypePool.
 */
class TypePool {
    
    private val classToBinderMap = mutableMapOf<Class<*>, ItemBinder>()
    private val typeToBinderMap = mutableMapOf<Int, ItemBinder>()
    private val typeToClassMap = mutableMapOf<Int, Class<*>>()
    
    private var typeCount = 0
    
    /**
     * Register a binder for a specific class type.
     * The type will be assigned automatically.
     */
    fun register(binder: ItemBinder) {
        val itemClass = binder.getItemClass()
        
        if (classToBinderMap.containsKey(itemClass)) {
            throw IllegalStateException("Binder for ${itemClass.name} already registered")
        }
        
        val type = typeCount++
        classToBinderMap[itemClass] = binder
        typeToBinderMap[type] = binder
        typeToClassMap[type] = itemClass
    }
    
    /**
     * Register a binder for a specific type.
     */
    fun register(type: Int, binder: ItemBinder) {
        val itemClass = binder.getItemClass()
        
        if (classToBinderMap.containsKey(itemClass)) {
            throw IllegalStateException("Binder for ${itemClass.name} already registered")
        }
        
        if (typeToBinderMap.containsKey(type)) {
            throw IllegalStateException("Type $type already registered")
        }
        
        classToBinderMap[itemClass] = binder
        typeToBinderMap[type] = binder
        typeToClassMap[type] = itemClass
        
        // Update type count if needed
        if (type >= typeCount) {
            typeCount = type + 1
        }
    }
    
    /**
     * Get the binder for a specific class.
     */
    fun getBinder(itemClass: Class<*>): ItemBinder? {
        return classToBinderMap[itemClass]
    }
    
    /**
     * Get the binder for a specific type.
     */
    fun getBinder(type: Int): ItemBinder? {
        return typeToBinderMap[type]
    }
    
    /**
     * Get the class for a specific type.
     */
    fun getClass(type: Int): Class<*>? {
        return typeToClassMap[type]
    }
    
    /**
     * Get the type for a specific class.
     */
    fun getType(itemClass: Class<*>): Int? {
        val binder = classToBinderMap[itemClass] ?: return null
        return typeToBinderMap.entries.find { it.value === binder }?.key
    }
    
    /**
     * Get the number of registered types.
     */
    fun getTypeCount(): Int = typeCount
    
    /**
     * Clear all registered binders.
     */
    fun clear() {
        classToBinderMap.clear()
        typeToBinderMap.clear()
        typeToClassMap.clear()
        typeCount = 0
    }
}
