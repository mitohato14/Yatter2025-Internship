package com.dmm.bootcamp.yatter2025.ui.profile

import com.dmm.bootcamp.yatter2025.ui.profile.bindinmodel.UserBindingModel

data class UserProfileUiState(
    val userBindingModel: UserBindingModel,
    val isLoading: Boolean,
    val isRefreshing: Boolean,
) {
    companion object {
        fun empty(): UserProfileUiState = UserProfileUiState(
            userBindingModel = UserBindingModel(
                username = "",
                displayName = "",
                note = null,
                avatar = null,
                header = null,
                followingCount = 0,
                followerCount = 0,
            ),
            isLoading = false,
            isRefreshing = false,
        )
    }
}