package com.dmm.bootcamp.yatter2025.ui.timeline

import com.dmm.bootcamp.yatter2025.ui.timeline.bindingmodel.YweetBindingModel

data class PublicTimelineUiState(
    val yweetList: List<YweetBindingModel>,
    val isLoading: Boolean,
    val isRefreshing: Boolean,
) {
    companion object {
        fun empty(): PublicTimelineUiState = PublicTimelineUiState(
            yweetList = emptyList(),
            isLoading = false,
            isRefreshing = false,
        )
    }
}