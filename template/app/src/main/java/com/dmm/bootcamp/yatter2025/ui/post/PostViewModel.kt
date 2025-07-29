package com.dmm.bootcamp.yatter2025.ui.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmm.bootcamp.yatter2025.common.navigation.Destination
import com.dmm.bootcamp.yatter2025.common.navigation.PopBackDestination
import com.dmm.bootcamp.yatter2025.domain.service.GetLoginUserService
import com.dmm.bootcamp.yatter2025.usecase.post.PostYweetUseCase
import com.dmm.bootcamp.yatter2025.usecase.post.PostYweetUseCaseResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostViewModel(
    private val postYweetUseCase: PostYweetUseCase,
    private val getLoginUserService: GetLoginUserService,
) : ViewModel() {
    private val _uiState: MutableStateFlow<PostUiState> = MutableStateFlow(PostUiState.empty())
    val uiState: StateFlow<PostUiState> = _uiState.asStateFlow()

    private val _destination = MutableStateFlow<Destination?>(null)
    val destination: StateFlow<Destination?> = _destination.asStateFlow()
    fun onCreate() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            val me = getLoginUserService.execute()

            val snapshotBindingModel = uiState.value.bindingModel

            _uiState.update {
                it.copy(
                    bindingModel = snapshotBindingModel.copy(avatarUrl = me?.avatar?.toString()),
                    isLoading = false,
                )
            }
        }
    }

    fun onChangedYweetText(yweetText: String) {
        _uiState.update { it.copy( bindingModel = uiState.value.bindingModel.copy(yweetText = yweetText)) }
    }

    fun onClickPost() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            val result = postYweetUseCase.execute(
                content = uiState.value.bindingModel.yweetText,
                attachmentList = listOf()
            )
            when (result) {
                PostYweetUseCaseResult.Success -> {
                    _destination.value = PopBackDestination
                }
                is PostYweetUseCaseResult.Failure -> {

                }
            }
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    fun onClickNavIcon() {
        _destination.value = PopBackDestination
    }

    fun onCompleteNavigation() {
        _destination.value = null
    }
}