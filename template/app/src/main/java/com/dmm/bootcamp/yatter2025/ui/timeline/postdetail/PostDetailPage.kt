package com.dmm.bootcamp.yatter2025.ui.timeline.postdetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.dmm.bootcamp.yatter2025.ui.LocalNavController
import org.koin.androidx.compose.getViewModel

@Composable
fun PostDetailPage(
    postDetailViewModel: PostDetailViewModel = getViewModel(),
    yweetId: String
) {
    val uiState by postDetailViewModel.uiState.collectAsState()
    val destination by postDetailViewModel.destination.collectAsState()
    val navController = LocalNavController.current

    LaunchedEffect(destination) {
        destination?.navigate(navController)
        postDetailViewModel.onCompleteNavigation()
    }

    LifecycleEventEffect(event= Lifecycle.Event.ON_CREATE) {
        postDetailViewModel.onResume(yweetId)
    }

//    LifecycleEventEffect(event = Lifecycle.Event.ON_RESUME) {
//        postDetailViewModel.onResume(yweetId)
//    }

    PostDetailTemplate(
        yweetBindingModel = uiState.yweet,
        isLoading = uiState.isLoading,
        onClickNavIcon = postDetailViewModel::onClickNavIcon
    )
}