package com.dmm.bootcamp.yatter2025.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.getViewModel

@Composable
fun LoginPage(
    loginViewModel: LoginViewModel = getViewModel(),
) {
    val uiState: LoginUiState by loginViewModel.uiState.collectAsStateWithLifecycle()
    val destination by loginViewModel.destination.collectAsStateWithLifecycle()
    val navController = LocalNavController.current

    LaunchedEffect(destination) {
        destination?.let {
            it.navigate(navController)
            loginViewModel.onCompleteNavigation()
        }
    }

    LoginTemplate(
        userName = uiState.loginBindingModel.username,
        onChangedUserName = loginViewModel::onChangeUsername,
        password = uiState.loginBindingModel.password,
        onChangedPassword = loginViewModel::onChangePassword,
        isEnableLogin = uiState.isEnableLogin,
        isLoading = uiState.isLoading,
        onClickLogin = loginViewModel::onClickLogin,
        onClickRegister = loginViewModel::onClickRegister,
    )
}