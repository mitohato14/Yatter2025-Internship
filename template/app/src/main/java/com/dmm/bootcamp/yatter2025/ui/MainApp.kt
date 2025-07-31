package com.dmm.bootcamp.yatter2025.ui

import LoginDestination
import PublicTimelineDestination
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.getViewModel

val LocalNavController = compositionLocalOf<NavController> {
    error("Not set a NavController!")
}

@Composable
fun MainApp(
    mainViewModel: MainViewModel = getViewModel(),
) {
    val navController = rememberNavController()
    val startDestination = mainViewModel.startDestination.collectAsState(initial = null).value
    LifecycleEventEffect(
        event = Lifecycle.Event.ON_CREATE,
    ) {
        mainViewModel.onCreate()
    }
    CompositionLocalProvider(
        LocalNavController provides navController,
    ) {
        if (startDestination != null) {
            NavHost(
                navController = navController,
                startDestination = startDestination.route
            ) {
                LoginDestination.createNode(this)
                PublicTimelineDestination.createNode(this)
            }
        }
    }
}