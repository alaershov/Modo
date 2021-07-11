package com.github.terrakok.modo

/**
 * Holder of current navigation state
 */
data class NavigationState(
    val chain: List<NavigationStateEntry> = emptyList()
)

/**
 * Element of current navigation stack
 */
interface NavigationStateEntry {
    val screen: Screen
}

/**
 * Navigation entry holding a simple Screen
 */
data class NavigationStateScreenEntry(
    override val screen: Screen
) : NavigationStateEntry
