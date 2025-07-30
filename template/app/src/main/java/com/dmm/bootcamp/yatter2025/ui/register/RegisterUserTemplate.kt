package com.dmm.bootcamp.yatter2025.ui.register

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dmm.bootcamp.yatter2025.domain.model.Password
import com.dmm.bootcamp.yatter2025.ui.theme.Yatter2025Theme

@Composable
fun RegisterUserTemplate(
    username: String,
    onChangeUsername: (String) -> Unit,
    password: String,
    onChangePassword: (String) -> Unit,
    isEnableRegister:Boolean,
    isLoading: Boolean,
    onClickRegister: () -> Unit,
    onClickLogin: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("新規登録")
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(8.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                    text = "ユーザー名"
                )
                OutlinedTextField(
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    value = username,
                    onValueChange = onChangeUsername,
                    placeholder = {
                        Text("username")
                    },
                )
                Text(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                    text = "パスワード"
                )
                OutlinedTextField(
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    value = password,
                    onValueChange = onChangePassword,
                    placeholder = {
                        Text("password")
                    },
                )
                Button(
                    enabled = isEnableRegister,
                    onClick = onClickRegister,
                    modifier = Modifier
                        .fillMaxWidth()
                    ) {
                    Text("新規登録")
                }
                Divider(modifier = Modifier.padding(vertical = 16.dp))
                Text(
                    text = "既にアカウントお持ちの方は",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.body2
                )
                TextButton(
                    onClick = onClickRegister,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("ログイン")
                }
            }
            if (isLoading) {
                CircularProgressIndicator()
            }
        }
    }
}

@Preview
@Composable
fun RegisterUserTemplatePreview(modifier: Modifier = Modifier) {
    Yatter2025Theme {
        Surface {
            RegisterUserTemplate(
                username = "username",
                onChangeUsername = {},
                password = "password",
                onChangePassword = {},
                isEnableRegister = true,
                isLoading = false,
                onClickRegister = {},
                onClickLogin = {},
            )
        }
    }
}