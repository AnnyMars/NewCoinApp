package ru.mobileup.template.core.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun CoilImageWidget(
    url: String,
    modifier: Modifier
) {
    val localContext = LocalContext.current

    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(localContext)
            .data(url)
            .crossfade(true)
            .build(),
        contentDescription = ""
    )
}