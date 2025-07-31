package com.dmm.bootcamp.yatter2025.ui.profile.bindinmodel

import com.dmm.bootcamp.yatter2025.domain.model.User
import com.dmm.bootcamp.yatter2025.domain.model.Yweet
import com.dmm.bootcamp.yatter2025.ui.timeline.bindingmodel.YweetBindingModel

object UserConverter {
    fun convertToBindingModel(user: User): UserBindingModel = UserBindingModel(
        username = user.username.value,
        displayName = user.displayName,
        note = user.note,
        avatar = user.avatar.toString(),
        header = user.header.toString(),
        followingCount = user.followerCount,
        followerCount = user.followerCount
    )
}