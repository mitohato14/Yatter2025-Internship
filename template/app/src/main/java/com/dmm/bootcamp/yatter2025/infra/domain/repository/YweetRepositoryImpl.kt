package com.dmm.bootcamp.yatter2025.infra.domain.repository

import com.dmm.bootcamp.yatter2025.domain.model.Yweet
import com.dmm.bootcamp.yatter2025.domain.model.YweetId
import com.dmm.bootcamp.yatter2025.domain.repository.YweetRepository
import com.dmm.bootcamp.yatter2025.auth.TokenProvider
import com.dmm.bootcamp.yatter2025.infra.api.YatterApi
import com.dmm.bootcamp.yatter2025.infra.api.json.PostYweetJson
import com.dmm.bootcamp.yatter2025.infra.domain.converter.YweetConverter
import com.dmm.bootcamp.yatter2025.infra.pref.LoginUserPreferences
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import java.io.File

class YweetRepositoryImpl(
  private val yatterApi: YatterApi,
  private val tokenProvider: TokenProvider,
) : YweetRepository {
  override suspend fun findById(id: YweetId): Yweet? {
    val yweet = yatterApi.getYweetById(id.value)
    return YweetConverter.convertToDomainModel(yweet)
  }

  override suspend fun findAllPublic(): List<Yweet> = withContext(IO) {
    val yweetList = yatterApi.getPublicTimeline()
    YweetConverter.convertToDomainModel(yweetList)
  }

  override suspend fun findAllHome(): List<Yweet> = withContext(IO) {
    val yweetList = yatterApi.getHomeTimeline(tokenProvider.provide())
    YweetConverter.convertToDomainModel(yweetList)
  }

  override suspend fun create(
    content: String,
    attachmentList: List<File>
  ): Yweet = withContext(IO) {
    val yweetJson = yatterApi.postYweet(
      tokenProvider.provide(),
      PostYweetJson(
        content,
        listOf()
      )
    )
    YweetConverter.convertToDomainModel(yweetJson)
  }

  override suspend fun delete(yweet: Yweet) {
    TODO("Not yet implemented")
  }
}
