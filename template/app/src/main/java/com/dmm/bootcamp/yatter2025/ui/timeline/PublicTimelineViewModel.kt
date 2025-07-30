package com.dmm.bootcamp.yatter2025.ui.timeline

import PublicTimelineUiState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmm.bootcamp.yatter2025.domain.repository.YweetRepository
import com.dmm.bootcamp.yatter2025.ui.timeline.bindingmodel.converter.YweetConverter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PublicTimelineViewModel(
    private val yweetRepository: YweetRepository,
    ):ViewModel() {
    private val _uiState: MutableStateFlow<PublicTimelineUiState> =
        MutableStateFlow(PublicTimelineUiState.empty())
    val uiState: StateFlow<PublicTimelineUiState> = _uiState.asStateFlow()

    private suspend fun fetchPublicTimeline() {
        val yweetList = yweetRepository.findAllPublic()
        val yweetBindingModelList = YweetConverter.convertToBindingModel(yweetList) // 1
        _uiState.update{ currentState ->
            currentState.copy(
                yweetList = yweetBindingModelList
            )
        }
    }

    fun onResume(){
        viewModelScope.launch {
            _uiState.update{
                it.copy(isLoading = true)
            }
            fetchPublicTimeline()
            _uiState.update{
                it.copy(isLoading = false)
            }
        }
    }

    fun onRefresh() {
        TODO("Not yet implemented")
    }

}