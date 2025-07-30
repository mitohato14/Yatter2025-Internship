package com.dmm.bootcamp.yatter2025.ui.timeline

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.getViewModel


@Composable
fun PublicTimelinePage(
    publicTimelineViewModel: PublicTimelineViewModel = getViewModel(),
) {
    val uiState by publicTimelineViewModel.uiState.collectAsStateWithLifecycle()

    LifecycleEventEffect(event = Lifecycle.Event.ON_RESUME) {
        publicTimelineViewModel.onResume()
    }

    PublicTimelineTemplate(
        yweetList = uiState.yweetList,
        isLoading = uiState.isLoading,
        isRefreshing = uiState.isRefreshing,
        onRefresh = publicTimelineViewModel::onRefresh,
    )
}
