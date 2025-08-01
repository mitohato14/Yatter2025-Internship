package com.dmm.bootcamp.yatter2025.ui.timeline

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dmm.bootcamp.yatter2025.ui.theme.Yatter2025Theme
import com.dmm.bootcamp.yatter2025.ui.timeline.bindingmodel.YweetBindingModel

@Composable
fun TimelineTemplate(
    homeYweetList: List<YweetBindingModel>,
    publicYweetList: List<YweetBindingModel>,
    selectedTab: TimelineTab,
    isLoading: Boolean,
    isRefreshing: Boolean,
    onClickTab: (TimelineTab) -> Unit,
    onRefreshHome: () -> Unit,
    onRefreshPublic: () -> Unit,
    onClickPost: () -> Unit,
    onClickYweet: (yweetId: String) -> Unit,
    onClickAvater: (username: String) -> Unit,
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onClickPost) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "post"
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            TabRow(
                selectedTabIndex = selectedTab.ordinal,
            ) {
                TimelineTab.entries.forEach { tab ->
                    Tab(
                        selected = tab == selectedTab,
                        text = {
                            Text(tab.displayName)
                        },
                        onClick = {
                            onClickTab(tab)
                        }
                    )
                }
            }
            when (selectedTab) {
                TimelineTab.Home -> {
                    TimelineBlockTemplate(
                        yweetList = homeYweetList,
                        isLoading = isLoading,
                        isRefreshing = isRefreshing,
                        onRefresh = onRefreshHome,
                        onClickPost = onClickPost,
                        onClickYweet = onClickYweet,
                        onClickAvater = onClickAvater,
                    )
                }
                TimelineTab.Public -> {
                    TimelineBlockTemplate(
                        yweetList = publicYweetList,
                        isLoading = isLoading,
                        isRefreshing = isRefreshing,
                        onRefresh = onRefreshPublic,
                        onClickPost = onClickPost,
                        onClickYweet = onClickYweet,
                        onClickAvater = onClickAvater,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun TimelineTemplatePreview() {
    Yatter2025Theme {
        Surface {
            TimelineTemplate(
                homeYweetList = listOf(
                    YweetBindingModel(
                        id = "id1",
                        displayName = "display name1",
                        username = "username1",
                        avatar = null,
                        content = "preview content1",
                        attachmentImageList = listOf()
                    ),
                    YweetBindingModel(
                        id = "id2",
                        displayName = "display name2",
                        username = "username2",
                        avatar = null,
                        content = "preview content2",
                        attachmentImageList = listOf()
                    ),
                    YweetBindingModel(
                        id = "id3",
                        displayName = "display name3",
                        username = "username3",
                        avatar = null,
                        content = "preview content3",
                        attachmentImageList = listOf()
                    ),
                ),
                publicYweetList = listOf(
                    YweetBindingModel(
                        id = "id1",
                        displayName = "display name1",
                        username = "username1",
                        avatar = null,
                        content = "preview content1",
                        attachmentImageList = listOf()
                    ),
                    YweetBindingModel(
                        id = "id2",
                        displayName = "display name2",
                        username = "username2",
                        avatar = null,
                        content = "preview content2",
                        attachmentImageList = listOf()
                    ),
                    YweetBindingModel(
                        id = "id3",
                        displayName = "display name3",
                        username = "username3",
                        avatar = null,
                        content = "preview content3",
                        attachmentImageList = listOf()
                    ),
                ),
                isLoading = true,
                isRefreshing = false,
                selectedTab = TimelineTab.Home,
                onClickTab = {},
                onRefreshHome = {},
                onRefreshPublic = {},
                onClickPost = {},
                onClickYweet = {},
                onClickAvater = {},
            )
        }
    }
}