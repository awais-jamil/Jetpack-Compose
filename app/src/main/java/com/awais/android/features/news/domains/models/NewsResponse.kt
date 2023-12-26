package com.awais.android.features.news.domains.models

data class NewsResponse(
    val status: String,
    val totalResults: Long,
    val articles: List<NewsArticle>,
)