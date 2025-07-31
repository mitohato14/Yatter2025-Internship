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
                avatar = "",
                content = "",
                attachmentImageList = listOf(
                    ImageBindingModel(
                        id = "",
                        type = "image",
                        url = "",
                        description = ""
                    )
                )
            ),
            isLoading = false
        )
    }
}
