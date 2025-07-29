package com.dmm.bootcamp.yatter2025.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmm.bootcamp.yatter2025.common.navigation.Destination
import com.dmm.bootcamp.yatter2025.domain.service.CheckLoginService
import com.dmm.bootcamp.yatter2025.ui.login.LoginDestination
import com.dmm.bootcamp.yatter2025.ui.timeline.PublicTimelineDestination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val checkLoginService: CheckLoginService
) : ViewModel() {
    private val _startDestination = MutableStateFlow<Destination?>(null)
    val startDestination: StateFlow<Destination?> = _startDestination.asStateFlow()

    fun onCreate() {
        viewModelScope.launch {
            if(checkLoginService.execute()) {
                _startDestination.value = PublicTimelineDestination()
            } else {
                _startDestination.value = LoginDestination()
            }
        }
    }
}