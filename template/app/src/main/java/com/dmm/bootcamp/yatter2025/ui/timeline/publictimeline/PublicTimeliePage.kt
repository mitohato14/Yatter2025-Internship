package com.dmm.bootcamp.yatter2025.ui.timeline.publictimeline

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dmm.bootcamp.yatter2025.ui.LocalNavController
import org.koin.androidx.compose.getViewModel

@Composable
fun PublicTimelinePage(
    publicTimelineViewModel: PublicTimelineViewModel = getViewModel(),
) {
    val uiState by publicTimelineViewModel.uiState.collectAsStateWithLifecycle()
    val destination by publicTimelineViewModel.destination.collectAsStateWithLifecycle()
    val navController = LocalNavController.current

    LaunchedEffect(destination) {
        destination?.let {
            it.navigate(navController)
            publicTimelineViewModel.onCompleteNavigation()
        }
    }

    LifecycleEventEffect(event = Lifecycle.Event.ON_RESUME) {
        publicTimelineViewModel.onResume()
    }

    PublicTimelineTemplate(
        yweetList = uiState.yweetList,
        isLoading = uiState.isLoading,
        isRefreshing = uiState.isRefreshing,
        onRefresh = publicTimelineViewModel::onRefresh,
        onClickPost = publicTimelineViewModel::onClickPost,
        onClickYweet = publicTimelineViewModel::onClickYweet
    )
}