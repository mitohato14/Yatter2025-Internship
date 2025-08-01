package com.dmm.bootcamp.yatter2025.ui.timeline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmm.bootcamp.yatter2025.common.navigation.Destination
import com.dmm.bootcamp.yatter2025.domain.repository.YweetRepository
import com.dmm.bootcamp.yatter2025.ui.post.PostDestination
import com.dmm.bootcamp.yatter2025.ui.profile.UserProfileDestination
import com.dmm.bootcamp.yatter2025.ui.timeline.postdetail.PostDetailDestination
import com.dmm.bootcamp.yatter2025.ui.timeline.bindingmodel.converter.YweetConverter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TimelineViewModel(
    private val yweetRepository: YweetRepository,
) : ViewModel() {
    private val _uiState: MutableStateFlow<TimelineUiState> = MutableStateFlow(
        TimelineUiState.empty()
    )
    val uiState: StateFlow<TimelineUiState> = _uiState.asStateFlow()

    private val _destination = MutableStateFlow<Destination?>(null)
    val destination: StateFlow<Destination?> = _destination.asStateFlow()

    private suspend fun fetchPublicTimeline() {
        val yweetList = yweetRepository.findAllPublic()
        _uiState.update {
            it.copy(
                publicYweetList = YweetConverter.convertToBindingModel(yweetList),
            )
        }
    }

    private suspend fun fetchHomeTimeline() {
        val yweetList = yweetRepository.findAllHome()
        _uiState.update {
            it.copy(
                homeYweetList = YweetConverter.convertToBindingModel(yweetList)
            )
        }
    }

    fun onResume() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            when(uiState.value.selectedTab) {
                TimelineTab.Home -> fetchHomeTimeline()
                TimelineTab.Public -> fetchPublicTimeline()
            }
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    fun onRefreshPublic() {
        viewModelScope.launch {
            _uiState.update { it.copy(isRefreshing = true) }
            fetchPublicTimeline()
            _uiState.update { it.copy(isRefreshing = false) }
        }
    }
    fun onRefreshHome() {
        viewModelScope.launch {
            _uiState.update { it.copy(isRefreshing = true) }
            fetchHomeTimeline()
            _uiState.update { it.copy(isRefreshing = false) }
        }
    }

    fun onClickTab(tab: TimelineTab) {
        _uiState.update { it.copy(selectedTab = tab) }
        viewModelScope.launch{
            when(tab) {
                TimelineTab.Home -> {
                    _uiState.update { it.copy(isRefreshing = true) }
                    if (uiState.value.homeYweetList.isEmpty()) {
                        fetchHomeTimeline()
                    }
                    _uiState.update { it.copy(isRefreshing = false) }
                }
                TimelineTab.Public -> {
                    _uiState.update { it.copy(isRefreshing = true) }
                    if (uiState.value.publicYweetList.isEmpty()) {
                        fetchPublicTimeline()
                    }
                    _uiState.update { it.copy(isRefreshing = false) }
                }
            }
        }
    }

    fun onClickPost() {
        _destination.value = PostDestination()
    }

    fun onClickAvatar(username: String) {
        _destination.value = UserProfileDestination(username)
    }

    fun onClickYweet(yweetId: String) {
        _destination.value = PostDetailDestination(yweetId)
    }

    fun onCompleteNavigation() {
        _destination.value = null
    }
}