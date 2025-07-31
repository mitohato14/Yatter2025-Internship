# ツイート画面のdomain層実装

ツイート画面実装に必要なdomain層実装を行います。  

まずはQuery部分から実装します。  

必要なドメインモデルとして、`User`ドメインがありますが、すでに実装済みですね。

```Kotlin
package com.dmm.bootcamp.yatter2025.domain.model

import com.dmm.bootcamp.yatter2025.common.ddd.Entity
import java.net.URL

data class User(
  override val id: UserId,
  val username: Username,
  val displayName: String?,
  val note: String?,
  val avatar: URL,
  val header: URL,
  val followingCount: Int,
  val followerCount: Int,
) : Entity<UserId>(id)
```

続いてDomainServiceの実装を行います。  
必要なDomainServiceは`GetLoginUserService`です。  
このDomainServiceを利用して、ログイン済みのアカウント情報を取得し、ツイート画面のアイコン表示やトークン取得に活用します。  

```Kotlin
package com.dmm.bootcamp.yatter2025.domain.service

import com.dmm.bootcamp.yatter2025.domain.model.User

interface GetLoginUserService {
  suspend fun execute(): User?
}
```

ついでに、後々の実装で必要になる`GetLoginUsernameService`も実装しておきます。  
このDomainServiceは、ログイン済みのアカウントのユーザー名を取得し、ツイートに表示するメニューなどを変更する際に利用します。  

```Kotlin
package com.dmm.bootcamp.yatter2025.domain.service

import com.dmm.bootcamp.yatter2025.domain.model.Username

interface GetLoginUsernameService {
  fun execute(): Username?
}
```

最後にRepositoryの実装を行います。  

ユーザー情報を保持するための`UserRepository`を`domin/repository`に追加します。  

`UserRepository`でユーザーの検索や追加、更新といったユーザー周りの制御・操作を行えるようにします。  

```Kotlin
package com.dmm.bootcamp.yatter2025.domain.repository

import com.dmm.bootcamp.yatter2025.domain.model.User
import com.dmm.bootcamp.yatter2025.domain.model.Password
import com.dmm.bootcamp.yatter2025.domain.model.Username
import java.net.URL

interface UserRepository {
  suspend fun findLoginUser(disableCache: Boolean): User?

  suspend fun findByUsername(username: Username, disableCache: Boolean): User?

  suspend fun create(
    username: Username,
    password: Password
  ): User

  suspend fun update(
    me: User,
    newDisplayName: String?,
    newNote: String?,
    newAvatar: URL?,
    newHeader: URL?
  ): User

  suspend fun followings(): List<User>
  suspend fun followers(): List<User>

  suspend fun follow(me: User, username: Username)
  suspend fun unfollow(me: User, username: Username)
}
```

---

続いて、Command部分の実装です。  

ツイート処理は`YweetRepository`にメソッドを追加して実装します。  

ツイート処理専用の`DomainService`を作ることも可能ですが、Yweetを`YweetRepository`で集約することによりキャッシュを有効活用したりRepositoryパターンの概念としても合っていたりするため、`YweetRepository#create`で実施します。  

`YweetRepository`インターフェースを確認し、`create`メソッドが定義されていない場合は次のメソッドを追加します。  
`Yweet`を投稿(=新規作成)するため、`create`というメソッド名にしています。  

```Kotlin
interface YweetRepository {
  ...
  suspend fun create(
    cpntent: String,
    attachmentList: List<File>,
  ): Yweet
}
```

これにてツイート機能に必要なdomain層の実装は完了です。  

# [次の資料](./2_infra層実装.md)
