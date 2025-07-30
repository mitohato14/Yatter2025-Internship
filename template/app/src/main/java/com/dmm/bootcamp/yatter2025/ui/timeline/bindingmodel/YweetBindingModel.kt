package com.dmm.bootcamp.yatter2025.ui.timeline.bindingmodel

data class YweetBindingModel(
    val id: String,
    val displayName: String,
    val username: String,
    val avatar: String?,
    val content: String,
    val attachmentImageList: List<ImageBindingModel>
)