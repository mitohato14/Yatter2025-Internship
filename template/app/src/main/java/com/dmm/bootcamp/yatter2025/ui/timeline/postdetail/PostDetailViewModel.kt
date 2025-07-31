package com.dmm.bootcamp.yatter2025.ui.timeline.postdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmm.bootcamp.yatter2025.common.navigation.Destination
import com.dmm.bootcamp.yatter2025.common.navigation.PopBackDestination
import com.dmm.bootcamp.yatter2025.domain.model.YweetId
import com.dmm.bootcamp.yatter2025.domain.repository.YweetRepository
import com.dmm.bootcamp.yatter2025.ui.profile.UserProfileDestination
import com.dmm.bootcamp.yatter2025.ui.timeline.bindingmodel.converter.YweetConverter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostDetailViewModel(
    private val yweetRepository: YweetRepository
) : ViewModel(){
    private val _uiState: MutableStateFlow<PostDetailUiState> = MutableStateFlow(PostDetailUiState.empty())
    val uiState: StateFlow<PostDetailUiState> = _uiState.asStateFlow()

    private val _destination = MutableStateFlow<Destination?>(null)
    val destination: StateFlow<Destination?> = _destination.asStateFlow()

    private suspend fun fetchPostDetail(yweetId: String){
        val yweet = yweetRepository.findById(YweetId(yweetId))

        if (yweet != null) {
            _uiState.update {
                it.copy(
                    yweet = YweetConverter.convertToBindingModel(yweet)
                )
            }
        }
    }

    fun onCreate(yweetId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy( isLoading = true ) }
            fetchPostDetail(yweetId)
            _uiState.update { it.copy( isLoading = false )  }
        }
    }

    fun onClickAvatar(username: String) {
        _destination.value = UserProfileDestination(username)
    }

    fun onClickNavIcon() {
        _destination.value = PopBackDestination
    }

    fun onCompleteNavigation() {
        _destination.value = null
    }

}