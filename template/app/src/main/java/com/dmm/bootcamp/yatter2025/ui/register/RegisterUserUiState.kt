package com.dmm.bootcamp.yatter2025.ui.register

import com.dmm.bootcamp.yatter2025.ui.register.bindingmodel.RegisterUserBindingModel

data class RegisterUserUiState(
    val registerUserBindingModel: RegisterUserBindingModel,
    val isLoading: Boolean,
    val validUsername: Boolean,
    val validPassword: Boolean,
) {
    val isEnableRegister: Boolean = validUsername && validPassword

    companion object {
        fun empty(): RegisterUserUiState = RegisterUserUiState(
            registerUserBindingModel = RegisterUserBindingModel(
                username = "",
                password = "",
            ),
            isLoading = false,
            validUsername = false,
            validPassword = false,
        )
    }
}
