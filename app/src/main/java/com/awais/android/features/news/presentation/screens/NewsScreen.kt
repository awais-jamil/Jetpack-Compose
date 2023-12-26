package com.awais.android.features.news.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.awais.android.features.news.domains.models.NewsArticle
import com.awais.android.features.news.presentation.composables.NewsArticleCard
import com.awais.android.features.news.presentation.viewmodels.NewsViewModel

@Composable
fun NewsScreen(
    viewModel: NewsViewModel,
) {
    
    val newsArticles by viewModel.newsArticles.collectAsState()
    val loading by viewModel.loading.collectAsState()
    if (loading) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
            )
        }
    } else {
        NewsList(newsArticles = newsArticles)
    }
}

@Composable
fun NewsList(
    newsArticles: List<NewsArticle>,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
    ) {
        items(newsArticles.size) { article ->
            NewsArticleCard(newsArticle = newsArticles[article], onItemClick = {})
        }
    }
}