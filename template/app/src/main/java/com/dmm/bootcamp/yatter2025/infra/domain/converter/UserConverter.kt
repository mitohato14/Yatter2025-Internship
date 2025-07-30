package com.dmm.bootcamp.yatter2025.infra.domain.converter

import com.dmm.bootcamp.yatter2025.domain.model.User
import com.dmm.bootcamp.yatter2025.domain.model.UserId
import com.dmm.bootcamp.yatter2025.domain.model.Username
import com.dmm.bootcamp.yatter2025.infra.api.json.UserJson
import java.net.URL

object UserConverter {
  fun convertToDomainModel(json: UserJson) = User(
    id = UserId(json.id),
    username = Username(json.username),
    displayName = json.displayName,
    note = json.note,
    avatar = json.avatar?.let {
      if (it.isBlank()) {
        null
      } else {
        URL(it)
      }
                              },
    header = json.header?.let {
      if (it.isBlank()) {
      null
    } else {
      URL(it)
    } },
    followingCount = json.followingCount,
    followerCount = json.followersCount,
  )
}
