package com.dmm.bootcamp.yatter2025.ui.timeline.postdetail

import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dmm.bootcamp.yatter2025.common.navigation.Destination

class PostDetailDestination(
    private val yweetId: String?
) : Destination(ROUTE) {
    override fun buildRoute(): String {
        return buildString {
            append(ROUTE_PATH)
            if (yweetId != null) {
                append("?$KEY_YWEET_ID=$yweetId")
                Log.d("PostDetailDestination", "buildRoute: $yweetId")
            }
        }
    }
    companion object {
        private const val ROUTE_PATH = "detail"
        private const val KEY_YWEET_ID = "yweetId"
        private const val ROUTE = "$ROUTE_PATH?$KEY_YWEET_ID={$KEY_YWEET_ID}"

        fun createNode(builder: NavGraphBuilder) {
            Log.d("PostDetailDestination", "createNode: ")
            builder.composable(
                route = ROUTE,
                arguments = listOf(
                    navArgument(KEY_YWEET_ID) {
                        defaultValue = ""
                        type = NavType.StringType
                    },
                ),
                ) { backStackEntry ->
                val yweetId = requireNotNull(backStackEntry.arguments?.getString(KEY_YWEET_ID))
                PostDetailPage(yweetId = yweetId)
            }
        }
    }
}