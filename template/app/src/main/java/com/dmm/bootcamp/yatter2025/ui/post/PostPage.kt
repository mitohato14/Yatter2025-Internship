package com.dmm.bootcamp.yatter2025.ui.post

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dmm.bootcamp.yatter2025.ui.LocalNavController
import org.koin.androidx.compose.getViewModel

@Composable
fun PostPage(
    postViewModel: PostViewModel = getViewModel(),
) {
    val uiState by postViewModel.uiState.collectAsStateWithLifecycle()
    val destination by postViewModel.destination.collectAsStateWithLifecycle()
    val navController = LocalNavController.current
    LaunchedEffect(destination) {
        destination?.navigate(navController)
        postViewModel.onCompleteNavigation()
    }
    LifecycleEventEffect(event = Lifecycle.Event.ON_CREATE) {
        postViewModel.onCreate()
    }
    PostTemplate(
        postBindingModel = uiState.bindingModel,
        isLoading = uiState.isLoading,
        canPost = uiState.canPost,
        onYweetTextChanged = postViewModel::onChangedYweetText,
        onClickPost = postViewModel::onClickPost,
        onClickNavIcon = postViewModel::onClickNavIcon,
    )
}