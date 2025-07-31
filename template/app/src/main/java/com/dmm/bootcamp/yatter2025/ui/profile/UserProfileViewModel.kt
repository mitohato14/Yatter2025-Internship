package com.dmm.bootcamp.yatter2025.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmm.bootcamp.yatter2025.common.navigation.Destination
import com.dmm.bootcamp.yatter2025.common.navigation.PopBackDestination
import com.dmm.bootcamp.yatter2025.domain.model.Username
import com.dmm.bootcamp.yatter2025.domain.repository.UserRepository
import com.dmm.bootcamp.yatter2025.ui.profile.bindinmodel.UserConverter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserProfileViewModel(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UserProfileUiState> = MutableStateFlow(UserProfileUiState.empty())
    val uiState: StateFlow<UserProfileUiState> = _uiState.asStateFlow()

    private val _destination = MutableStateFlow<Destination?>(null)
    val destination: StateFlow<Destination?> = _destination.asStateFlow()

    private suspend fun fetchUserProfile(username: String) {
        val user = userRepository.findByUsername(
            username = Username(username),
            disableCache = true
        )

        if (user != null) {
            _uiState.update {
                it.copy(
                    userBindingModel = UserConverter.convertToBindingModel(user)
                )
            }
        }
    }

    fun onCreate(username: String) {
        viewModelScope.launch {
            _uiState.update { it.copy( isLoading = true ) }
            fetchUserProfile(username)
            _uiState.update { it.copy( isLoading = false )  }
        }
    }

    fun onRefresh(username: String) {
        viewModelScope.launch {
            _uiState.update { it.copy( isRefreshing = true ) }
            fetchUserProfile(username)
            _uiState.update { it.copy( isRefreshing = false )  }
        }
    }

    fun onCompleteNavigation() {
        _destination.value = null
    }

    fun onClickNavIcon() {
        _destination.value = PopBackDestination
    }
}