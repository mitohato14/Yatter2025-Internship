package com.dmm.bootcamp.yatter2025.infra.domain.service

interface CheckLoginService {
    suspend fun execute(): Boolean
}