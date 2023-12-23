package wskim.aos.simpledutch.ui.feature.splash.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import wskim.aos.simpledutch.R

@Composable
fun SplashImage() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(R.drawable.logo).build(),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.width(180.dp).height(276.dp),
            placeholder = painterResource(id = R.drawable.logo)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun SplashImagePreview() {
    SplashImage()
}