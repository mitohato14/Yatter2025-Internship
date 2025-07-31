package com.dmm.bootcamp.yatter2025.ui.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dmm.bootcamp.yatter2025.ui.LocalNavController
import org.koin.androidx.compose.getViewModel

@Composable
fun UserProfilePage(
    userProfileViewModel: UserProfileViewModel = getViewModel(),
    username: String,
) {
    val uiState by userProfileViewModel.uiState.collectAsStateWithLifecycle()
    val destination by userProfileViewModel.destination.collectAsStateWithLifecycle()
    val navController = LocalNavController.current

    LaunchedEffect(destination) {
        destination?.navigate(navController)
        userProfileViewModel.onCompleteNavigation()
    }

    LifecycleEventEffect(event = Lifecycle.Event.ON_CREATE) {
        userProfileViewModel.onCreate(username)
    }

    UserProfileTemplate(
        userBindingModel = uiState.userBindingModel,
        isLoading = uiState.isLoading,
        isRefreshing = uiState.isRefreshing,
        onClickNavIcon = userProfileViewModel::onClickNavIcon,
    )
}