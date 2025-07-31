package com.dmm.bootcamp.yatter2025.ui.timeline

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dmm.bootcamp.yatter2025.common.navigation.Destination
import okhttp3.Route

class PostDetailDestination : Destination(ROUTE) {
    companion object {
        private const val ROUTE = "postDetail"

        fun createNode(builder: NavGraphBuilder) {
            builder.composable(ROUTE) {
                PostDetailPage()
            }
        }
    }
}