package com.multitype.core

/**
 * Constants for MultiType.
 */
object MultiTypeConstants {
    
    /**
     * Default view type.
     */
    const val DEFAULT_VIEW_TYPE = 0
    
    /**
     * Invalid position.
     */
    const val INVALID_POSITION = -1
    
    /**
     * Minimum span size.
     */
    const val MIN_SPAN_SIZE = 1
    
    /**
     * Maximum span size.
     */
    const val MAX_SPAN_SIZE = 100
    
    /**
     * Default animation duration in milliseconds.
     */
    const val DEFAULT_ANIMATION_DURATION = 300L
    
    /**
     * Default fade alpha.
     */
    const val DEFAULT_FADE_ALPHA = 0f
}

/**
 * View type constants.
 */
object ViewTypeConstants {
    
    /**
     * Header view type.
     */
    const val HEADER_VIEW_TYPE = -1
    
    /**
     * Footer view type.
     */
    const val FOOTER_VIEW_TYPE = -2
    
    /**
     * Empty view type.
     */
    const val EMPTY_VIEW_TYPE = -3
    
    /**
     * Loading view type.
     */
    const val LOADING_VIEW_TYPE = -4
    
    /**
     * Error view type.
     */
    const val ERROR_VIEW_TYPE = -5
}
