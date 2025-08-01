package com.dmm.bootcamp.yatter2025.usecase.register

import com.dmm.bootcamp.yatter2025.domain.model.Password
import com.dmm.bootcamp.yatter2025.domain.model.Username

interface RegisterUserUseCase {
  suspend fun execute(
    username: Username,
    password: Password,
  ): RegisterUserUseCaseResult
}
