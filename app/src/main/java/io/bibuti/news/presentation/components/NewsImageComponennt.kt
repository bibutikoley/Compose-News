package io.bibuti.news.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage

@Composable
fun NewsImageComponent(url: String, modifier: Modifier) {
    AsyncImage(
        model = url,
        contentDescription = "",
        modifier = modifier,
        onLoading = {
        },
        onError = {
        },
        onSuccess = {
        }
    )
}
