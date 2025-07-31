package com.dmm.bootcamp.yatter2025.ui.timeline.hometimeline

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

data class HomeTimelineViewModel(
    private val yweetRepository: YweetRepository,
) : ViewModel() {
    private val _uiState: MutableStateFlow<HomeTimelineUiState> = MutableStateFlow(HomeTimelineUiState.empty())
    val uiState: StateFlow<HomeTimelineUiState> = _uiState.asStateFlow()

    private val _destination = MutableStateFlow<Destination?>(null)
    val destination: StateFlow<Destination?> = _destination.asStateFlow()

    private suspend fun fetchHomeTimeline() {
        val yweetList = yweetRepository.findAllHome()
        _uiState.update {
            it.copy(
                yweetList = YweetConverter.convertToBindingModel(yweetList)
            )
        }
    }

    fun onResume() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            fetchHomeTimeline()
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    fun onRefresh() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            fetchHomeTimeline()
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    fun onClickAvatar(username: String) {
        _destination.value = UserProfileDestination(username)
    }

    fun onClickPost() {
        _destination.value = PostDestination()
    }

    fun onClickYweet(yweetId: String) {
        _destination.value = PostDetailDestination(yweetId)
    }

    fun onCompleteNavigation() {
        _destination.value = null
    }
}