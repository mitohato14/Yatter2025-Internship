package com.dmm.bootcamp.yatter2025.ui.timeline.postdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dmm.bootcamp.yatter2025.ui.theme.Yatter2025Theme
import com.dmm.bootcamp.yatter2025.ui.timeline.bindingmodel.ImageBindingModel
import com.dmm.bootcamp.yatter2025.ui.timeline.bindingmodel.YweetBindingModel

@Composable
fun PostDetailTemplate(
    yweetBindingModel: YweetBindingModel,
    isLoading: Boolean,
    onClickNavIcon: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                        Text("ポストの詳細")
                },
                navigationIcon = {
                    IconButton(onClick = onClickNavIcon) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "戻る"
                        )
                    }
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            if (isLoading) {
                Text("読み込み中")
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .size(64.dp),
                            model = yweetBindingModel.avatar,
                            contentDescription = "アバター画像",
                            contentScale = ContentScale.Crop
                        )
                        Column {
                            Text(
                                text = yweetBindingModel.displayName,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(
                                        style = SpanStyle(
                                            color = MaterialTheme.colors.onBackground.copy(alpha = ContentAlpha.medium)
                                        )
                                    ) {
                                        append("@${yweetBindingModel.username}")
                                    }
                                },
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    Text(
                        modifier = Modifier
                        .padding(horizontal = 16.dp),
                        text = yweetBindingModel.content,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PostDetailTemplatePreview() {
    Yatter2025Theme {
        Surface {
            PostDetailTemplate(
                yweetBindingModel = YweetBindingModel(
                    id = "id",
                    displayName = "mito",
                    username = "mitohato14",
                    avatar = "https://avatars.githubusercontent.com/u/19385268?v=4",
                    content = "preview content",
                    attachmentImageList = listOf(
                        ImageBindingModel(
                            id = "id",
                            type = "image",
                            url = "https://avatars.githubusercontent.com/u/39693306?v=4",
                            description = "icon"
                        )
                    )
                ),
                isLoading = false,
                onClickNavIcon = {}
            )
        }
    }
}