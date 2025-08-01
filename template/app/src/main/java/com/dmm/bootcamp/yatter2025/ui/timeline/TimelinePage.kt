package com.dmm.bootcamp.yatter2025.ui.timeline

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dmm.bootcamp.yatter2025.ui.LocalNavController
import org.koin.androidx.compose.getViewModel

@Composable
fun TImelinePage(
    viewModel: TimelineViewModel = getViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val destination by viewModel.destination.collectAsStateWithLifecycle()
    val navController = LocalNavController.current

    LaunchedEffect(destination) {
        destination?.let {
            it.navigate(navController)
            viewModel.onCompleteNavigation()
        }
    }

    LifecycleEventEffect(event = Lifecycle.Event.ON_RESUME) {
        viewModel.onResume()
    }

    TimelineTemplate(
        homeYweetList = uiState.homeYweetList,
        publicYweetList = uiState.publicYweetList,
        selectedTab = uiState.selectedTab,
        isLoading = uiState.isLoading,
        isRefreshing = uiState.isRefreshing,
        onClickTab = viewModel::onClickTab,
        onRefreshHome =  viewModel::onRefreshHome,
        onRefreshPublic = viewModel::onRefreshPublic,
        onClickPost = viewModel::onClickPost,
        onClickYweet = viewModel::onClickYweet,
        onClickAvater = viewModel::onClickAvatar
    )
}