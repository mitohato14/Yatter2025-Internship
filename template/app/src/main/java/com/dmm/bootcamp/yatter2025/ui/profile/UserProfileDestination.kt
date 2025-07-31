package com.dmm.bootcamp.yatter2025.ui.profile

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.request.ImageRequest
import com.dmm.bootcamp.yatter2025.common.navigation.Destination

class UserProfileDestination(
    private val username: String?
) : Destination(ROUTE) {
    override fun buildRoute(): String {
        return buildString {
            append(ROUTE_PATH)
            if (username != null) {
                append("$KEY_USERNAME=$username")
            }
        }
    }

    companion object {
        const val ROUTE_PATH = "userProfile"
        const val KEY_USERNAME = "username"
        const val ROUTE = "$ROUTE_PATH?$KEY_USERNAME={$KEY_USERNAME}"

        fun createNode(builder: NavGraphBuilder) {
            builder.composable(
                route = ROUTE,
                arguments = listOf(
                    navArgument(KEY_USERNAME) {
                        defaultValue = ""
                        type = NavType.StringType
                    },
                ),
            ) { backStackEntry ->
                val username = requireNotNull(backStackEntry.arguments?.getString(KEY_USERNAME))
                UserProfilePage(username = username)
            }
        }
    }
}