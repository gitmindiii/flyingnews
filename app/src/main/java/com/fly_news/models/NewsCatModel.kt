package com.fly_news.models

class NewsCatModel( val status: String = "",
                    val message: String = "",
                    val data: List<CatData>)

data class CatData(val categoryId: String, val categoryName: String,var page:String="1")