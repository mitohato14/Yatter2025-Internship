package com.dmm.bootcamp.yatter2025.ui.timeline.hometimeline

import com.dmm.bootcamp.yatter2025.ui.timeline.bindingmodel.YweetBindingModel

data class HomeTimelineUiState(
    val yweetList: List<YweetBindingModel>,
    val isLoading: Boolean,
    val isRefleshing: Boolean,
) {
    companion object {
        fun empty(): HomeTimelineUiState = HomeTimelineUiState(
            yweetList = emptyList(),
            isLoading = false,
            isRefleshing = false,
        )
    }
}
