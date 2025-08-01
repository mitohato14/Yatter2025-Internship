package com.dmm.bootcamp.yatter2025.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.dmm.bootcamp.yatter2025.ui.login.LoginDestination
import com.dmm.bootcamp.yatter2025.ui.post.PostDestination
import com.dmm.bootcamp.yatter2025.ui.profile.UserProfileDestination
import com.dmm.bootcamp.yatter2025.ui.register.RegisterUserDestination
import com.dmm.bootcamp.yatter2025.ui.timeline.TimelineDestination
import com.dmm.bootcamp.yatter2025.ui.timeline.postdetail.PostDetailDestination
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
                RegisterUserDestination.createNode(this)
//                PublicTimelineDestination.createNode(this)
                PostDestination.createNode(this)
//                HomeTimelineDestination.createNode(this)
                PostDetailDestination.createNode(this)
                UserProfileDestination.createNode(this)
                TimelineDestination.createNode(this)
            }
        }
    }
}