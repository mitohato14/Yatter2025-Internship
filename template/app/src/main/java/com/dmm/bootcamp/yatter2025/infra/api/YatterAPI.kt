package com.dmm.bootcamp.yatter2025.infra.api

import com.dmm.bootcamp.yatter2025.infra.api.json.UserJson
import com.dmm.bootcamp.yatter2025.infra.api.json.CreateUserJson
import com.dmm.bootcamp.yatter2025.infra.api.json.LoginRequestBodyJson
import com.dmm.bootcamp.yatter2025.infra.api.json.LoginResponseJson
import com.dmm.bootcamp.yatter2025.infra.api.json.PostYweetJson
import com.dmm.bootcamp.yatter2025.infra.api.json.YweetJson
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface YatterApi {

  @POST("auth/login")
  suspend fun login(
    @Body requestBody: LoginRequestBodyJson,
  ): LoginResponseJson

  @GET("timelines/home")
  suspend fun getHomeTimeline(
    @Header("Authentication") token: String,
    @Query("only_image") onlyImage: Boolean = false,
    @Query("offset") offset: Int = 0,
    @Query("limit") limit: Int = 40
  ): List<YweetJson>

  @GET("timelines/public")
  suspend fun getPublicTimeline(
    @Query("only_image") onlyImage: Boolean = false,
    @Query("offset") offset: Int = 0,
    @Query("limit") limit: Int = 80
  ): List<YweetJson>

  @POST("users")
  suspend fun createNewUser(
    @Body userJson: CreateUserJson
  ): UserJson

  @GET("users/{username}")
  suspend fun getUserByUsername(
    @Path("username") username: String
  ): UserJson

  @POST("yweets")
  suspend fun postYweet(
    @Header("Authentication") token: String,
    @Body yweetJson: PostYweetJson
  ): YweetJson

  @GET("yweets/{id}")
  suspend fun getYweetById(
    @Path("id") yweetId: String
  ): YweetJson
}
