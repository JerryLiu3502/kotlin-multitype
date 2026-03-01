package com.multitype.core

/**
 * Wrapper for items with position information.
 * 
 * This is similar to Drakeet's Position.
 */
data class Position(
    val item: Any,
    val position: Int,
    val viewType: Int
)

/**
 * Helper for managing item positions.
 */
object PositionHelper {
    
    /**
     * Create a Position from item and position.
     */
    fun create(item: Any, position: Int, viewType: Int = 0): Position {
        return Position(item, position, viewType)
    }
    
    /**
     * Get position from list of positions.
     */
    fun getPosition(positions: List<Position>, item: Any): Int? {
        return positions.find { it.item == item }?.position
    }
    
    /**
     * Filter positions by view type.
     */
    fun filterByViewType(positions: List<Position>, viewType: Int): List<Position> {
        return positions.filter { it.viewType == viewType }
    }
    
    /**
     * Get positions for items.
     */
    fun getPositions(items: List<Any>, viewTypeProvider: (Any) -> Int = { 0 }): List<Position> {
        return items.mapIndexed { index, item ->
            Position(item, index, viewTypeProvider(item))
        }
    }
}
