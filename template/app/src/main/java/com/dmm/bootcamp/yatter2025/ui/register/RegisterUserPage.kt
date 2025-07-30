package com.dmm.bootcamp.yatter2025.ui.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dmm.bootcamp.yatter2025.ui.LocalNavController
import org.koin.androidx.compose.getViewModel

@Composable
fun RegisterUserPage(
    registerUserViewModel: RegisterUserViewModel = getViewModel()
) {
    val uiState: RegisterUserUiState by registerUserViewModel.uiState.collectAsStateWithLifecycle()
    val destination by registerUserViewModel.destination.collectAsStateWithLifecycle()
    val navController = LocalNavController.current

    LaunchedEffect(destination) {
        destination?.let {
            it.navigate(navController)
            registerUserViewModel.onCompleteNavigation()
        }
    }

    RegisterUserTemplate(
        username = uiState.registerUserBindingModel.username,
        onChangeUsername = registerUserViewModel::onChangeUsername,
        password = uiState.registerUserBindingModel.password,
        onChangePassword = registerUserViewModel::onChangePassword,
        isEnableRegister = uiState.isEnableRegister,
        isLoading = uiState.isLoading,
        onClickRegister = registerUserViewModel::onClickRegister,
        onClickLogin = registerUserViewModel::onClickLogin,
    )
}