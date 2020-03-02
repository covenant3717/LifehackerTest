package com.covenant.lifehackertest.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Evgeny Kuksov 02.03.2020
 */

data class Post(
    @SerializedName("cat_cover") val cat_cover: CatCover,
    @SerializedName("author_name") val author_name: String = "",
    @SerializedName("title") val title: Content,
    @SerializedName("content") val content: Content
)

data class Content(
    @SerializedName("rendered") val rendered: String = ""
)

data class CatCover(
    @SerializedName("sizes") val sizes: PicSizes
)

data class PicSizes(
    @SerializedName("large") val large: String = "",
    @SerializedName("mobile") val mobile: String = ""
)