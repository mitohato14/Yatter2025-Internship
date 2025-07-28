package com.dmm.bootcamp.yatter2025.ui.timeline.bindingmodel

import com.dmm.bootcamp.yatter2025.domain.model.Yweet
import com.dmm.bootcamp.yatter2025.ui.timeline.bindingmodel.YweetBindingModel
import com.dmm.bootcamp.yatter2025.ui.timeline.converter.ImageConverter

object YweetConverter{
    fun convertToBindingModel(yweetList: List<Yweet>): List<YweetBindingModel> = yweetList.map { convertToBindingModel(it) }

    fun convertToBindingModel(yweet: Yweet): YweetBindingModel = YweetBindingModel(
        id = yweet.id.value,
        displayName = yweet.user.displayName ?: "",
        username = yweet.user.username.value,
        avatar = yweet.user.avatar.toString(),
        content = yweet.content,
        attachmentImageList = ImageConverter.convertToBindingModel(yweet.attachmentImageList)
    )

}