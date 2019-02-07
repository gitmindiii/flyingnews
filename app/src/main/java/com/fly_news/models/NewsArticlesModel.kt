package com.fly_news.models

import java.io.Serializable


class NewsArticlesModel(val status: String = "",
                        val totalResults: String = "",
                        val articles: List<ArticlesData>):Serializable


data class ArticlesData(val source: SourceData,
                        val author: String,
                        val title: String,
                        val description: String,
                        val url: String,
                        val urlToImage: String,
                        val publishedAt: String,
                        val content: String):Serializable



data class SourceData(val id: String,
                        val name: String):Serializable