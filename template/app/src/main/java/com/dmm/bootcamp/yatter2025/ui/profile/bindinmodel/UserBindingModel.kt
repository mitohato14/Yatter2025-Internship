package com.dmm.bootcamp.yatter2025.ui.profile.bindinmodel

data class UserBindingModel(
    val username: String,
    val displayName: String,
    val note: String?,
    val avatar: String?,
    val header: String?,
    val followingCount: Int,
    val followerCount:Int,
)
