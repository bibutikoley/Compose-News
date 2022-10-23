package io.bibuti.news.presentation.screens.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import io.bibuti.news.DevicePreviews
import io.bibuti.news.presentation.ui.theme.NewsTheme

@Destination(start = true)
@Composable
fun SplashScreen() {
    NewsTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Box {
                Text(
                    text = "SplashScreen",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@DevicePreviews
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}
