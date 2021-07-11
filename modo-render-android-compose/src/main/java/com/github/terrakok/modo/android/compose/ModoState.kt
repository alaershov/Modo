package com.github.terrakok.modo.android.compose

import android.os.Bundle
import android.util.Log
import com.github.terrakok.modo.*

private var modoInitialized: Boolean = false

fun Modo.init(bundle: Bundle?, firstScreen: Screen) {
    if (bundle == null) {
        if (!modoInitialized) {
            Log.d("Modo", "Activity first launch")
            modoInitialized = true
        } else {
            Log.d("Modo", "Activity re-launch")
        }
        forward(firstScreen)
    } else {
        if (!modoInitialized) {
            Log.d("Modo", "Activity restored after process death")
            modoInitialized = true
            val restoredState = bundle.restoreNavigationState()
            if (restoredState.chain.isNotEmpty()) {
                restore(restoredState)
            }
        } else {
            Log.d("Modo", "Activity restored after rotation")
            //do nothing
        }
    }
}

// TODO fix state restoration - make NavigationStateScreenEntry saveable to parcel without Parcelable implementation.
private const val KEY_NAVIGATION_STATE = "ket_nav_state"
private fun Bundle.restoreNavigationState() = NavigationState(
//    getParcelableArray(KEY_NAVIGATION_STATE)
//        ?.toList()
//        .orEmpty()
//        .filterIsInstance<ComposeScreen>()
//        .map { NavigationStateScreenEntry(it) }
    listOf()
)

fun Modo.saveState(bundle: Bundle) {
//    bundle.putParcelableArray(
//        KEY_NAVIGATION_STATE,
//        state.chain.filterIsInstance<ComposeScreen>().toTypedArray()
//    )
}
