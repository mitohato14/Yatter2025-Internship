package com.dmm.bootcamp.yatter2025.ui.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.dmm.bootcamp.yatter2025.ui.profile.bindinmodel.UserBindingModel
import com.dmm.bootcamp.yatter2025.ui.theme.Yatter2025Theme

@Composable
fun UserProfileTemplate(
    userBindingModel: UserBindingModel,
    isLoading: Boolean,
    isRefreshing: Boolean,
    onClickNavIcon: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("プロフィール")
                },
                navigationIcon = {
                    IconButton(onClick = onClickNavIcon) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "戻る")
                    }
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(96.dp),
                model = userBindingModel.header,
                contentScale = ContentScale.Crop,
                contentDescription = "ヘッダー画像"
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 64.dp, start = 8.dp, end = 8.dp)
            ) {

                AsyncImage(
                    modifier = Modifier
                        .size(64.dp),
                    model = userBindingModel.avatar,
                    contentDescription = "アバター画像",
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = userBindingModel.displayName ?: "",
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.h4.fontSize
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colors.onBackground.copy(alpha = ContentAlpha.medium)
                            )
                        ) {
                            append("@${userBindingModel.username}")
                        }
                    },
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                )
                Text(
                    text = userBindingModel.note ?: "",
                    fontSize = MaterialTheme.typography.body1.fontSize
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                ) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontSize = MaterialTheme.typography.body1.fontSize,
                                    fontWeight = FontWeight.Bold
                                )
                            ){
                                append(userBindingModel.followingCount.toString())
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colors.onBackground.copy(alpha = ContentAlpha.medium),
                                    fontSize = MaterialTheme.typography.caption.fontSize
                                )
                            ){
                                append("フォロー中")
                            }
                        },

                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontSize = MaterialTheme.typography.body1.fontSize,
                                    fontWeight = FontWeight.Bold
                                )
                            ){
                                append(userBindingModel.followerCount.toString())
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colors.onBackground.copy(alpha = ContentAlpha.medium),
                                    fontSize = MaterialTheme.typography.caption.fontSize
                                )
                            ){
                                append("フォロワー")
                            }
                        },
                        modifier = Modifier
                            .padding(start = 8.dp)
                    )
                }
            }
        }
    }

}

@Preview
@Composable
fun UserProfileTemplatePreview() {
    Yatter2025Theme {
        Surface {
            UserProfileTemplate(
                userBindingModel = UserBindingModel(
                    username = "KURO__48",
                    displayName = "KURO",
                    note = "Tokyo→Rits 情理 SN Swift書いてます 動画編集してます ドラムも少し叩けます",
                    avatar = "https://avatars.githubusercontent.com/u/19385268?v=4",
                    header = "https://avatars.githubusercontent.com/u/19385268?v=4",
                    followingCount = 0,
                    followerCount = 0,
                ),
                isLoading = false,
                isRefreshing = false,
                onClickNavIcon = {},
            )
        }
    }
}