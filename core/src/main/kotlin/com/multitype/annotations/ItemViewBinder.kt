package com.multitype.annotations

/**
 * Annotation for marking a class as an ItemViewBinder.
 * 
 * This is similar to Drakeet's @ItemViewBinder annotation.
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class ItemViewBinder(
    /**
     * The layout resource ID for this binder.
     */
    val layout: Int
)
