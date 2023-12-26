package com.awais.android.features.news.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.awais.android.features.news.domains.models.NewsArticle
import com.awais.android.features.news.domains.models.Source

@Composable
fun NewsArticleDetailsScreen(newsArticle: NewsArticle) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        newsArticle.urlToImage?.let { imageUrl ->
            val painter = rememberImagePainter(data = imageUrl)
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = newsArticle.title,
            style = MaterialTheme.typography.displayMedium
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = "Author: ${newsArticle.author ?: "Unknown"}",
            style = MaterialTheme.typography.titleSmall
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = "Published At: ${newsArticle.publishedAt}",
            style = MaterialTheme.typography.labelLarge
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = "Content: ${newsArticle.content}",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
fun PreviewNewsArticleDetailsScreen() {
    val sampleArticle = NewsArticle(
        source = Source("id", "Sample Source"),
        author = "John Doe",
        title = "Sample Title",
        description = "Sample Description",
        url = "https://www.sampleurl.com",
        urlToImage = "https://www.sampleimageurl.com/image.jpg",
        publishedAt = "2023-12-01T12:00:00Z",
        content = "Sample content..."
    )
    NewsArticleDetailsScreen(newsArticle = sampleArticle)
}
