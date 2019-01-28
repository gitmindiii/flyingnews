package com.fly_news.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class NewsArticlesModel(val status: String = "",
                        val totalResults: String = "",
                        val articles: List<ArticlesData>):Parcelable

@Parcelize
data class ArticlesData(val source: SourceData,
                        val author: String,
                        val title: String,
                        val description: String,
                        val url: String,
                        val urlToImage: String,
                        val publishedAt: String,
                        val content: String):Parcelable


@Parcelize
data class SourceData(val id: String,
                        val name: String):Parcelable