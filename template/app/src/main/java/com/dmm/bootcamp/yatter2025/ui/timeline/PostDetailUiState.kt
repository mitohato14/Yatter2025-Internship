package com.dmm.bootcamp.yatter2025.ui.timeline

import com.dmm.bootcamp.yatter2025.ui.timeline.bindingmodel.ImageBindingModel
import com.dmm.bootcamp.yatter2025.ui.timeline.bindingmodel.YweetBindingModel

data class PostDetailUiState(
    val yweet: YweetBindingModel,
    val isLoading: Boolean
) {
    companion object {
        fun empty(): PostDetailUiState = PostDetailUiState(
            yweet = YweetBindingModel(
                id = "",
                displayName = "",
                username = "",
                avatar = null,
                content = "",
                attachmentImageList = listOf()
            ),
            isLoading = false
        )
    }
}
