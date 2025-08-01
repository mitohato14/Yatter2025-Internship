package com.dmm.bootcamp.yatter2025.ui.timeline

import com.dmm.bootcamp.yatter2025.ui.timeline.bindingmodel.YweetBindingModel

data class TimelineUiState(
    val homeYweetList: List<YweetBindingModel>,
    val publicYweetList: List<YweetBindingModel>,
    val selectedTab: TimelineTab,
    val isLoading: Boolean,
    val isRefreshing: Boolean,
) {
    companion object {
        fun empty(): TimelineUiState = TimelineUiState(
            homeYweetList = emptyList(),
            publicYweetList = emptyList(),
            selectedTab = TimelineTab.Public,
            isLoading = false,
            isRefreshing = false,
        )
    }
}
