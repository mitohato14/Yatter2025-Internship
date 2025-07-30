package com.dmm.bootcamp.yatter2025.ui.register.bindingmodel

import java.net.URL

data class RegisterUserBindingModel(
    val username: String,
    val password: String,
    val displayName: String,
    val avatar: URL?,
    val header: URL?,
    val note: String,
)
