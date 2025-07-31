package com.dmm.bootcamp.yatter2025.ui.timeline.hometimeline

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dmm.bootcamp.yatter2025.ui.LocalNavController
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeTimelinePage(
    homeTimelineViewModel: HomeTimelineViewModel = getViewModel()
) {
    val uiState by homeTimelineViewModel.uiState.collectAsState()
    val destination by homeTimelineViewModel.destination.collectAsStateWithLifecycle()
    val navController = LocalNavController.current

    LaunchedEffect(destination) {
        destination?.let {
            it.navigate(navController)
            homeTimelineViewModel.onCompleteNavigation()
        }
    }

    LifecycleEventEffect(event = Lifecycle.Event.ON_RESUME) {
        homeTimelineViewModel.onResume()
    }

    HomeTimelineTemplate(
        yweetList = uiState.yweetList,
        isLoading = uiState.isLoading,
        isRefreshing = uiState.isRefleshing,
        onRefresh = homeTimelineViewModel::onRefresh,
        onClickPost = homeTimelineViewModel::onClickPost,
        onClickYweet = homeTimelineViewModel::onClickYweet,
        onClickAvatar = homeTimelineViewModel::onClickAvatar
    )
}
