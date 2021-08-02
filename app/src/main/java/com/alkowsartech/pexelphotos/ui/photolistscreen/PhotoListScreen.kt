package com.alkowsartech.pexelphotos.ui.photolistscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.alkowsartech.pexelphotos.data.remote.model.Photo
import com.alkowsartech.pexelphotos.viewmodel.DashboardViewModel


@Composable
fun PhotoListScreen(
    navController: NavController,
    viewModel: DashboardViewModel
) {

    Box(
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(4.dp)
    ) {

        val loadError by remember { viewModel.loadError }
        val isLoading by remember { viewModel.isLoading }
        val photos = viewModel.getPhotoPagination().collectAsLazyPagingItems()

        LazyColumn(contentPadding = PaddingValues(8.dp)) {


            items(photos.itemCount) { index ->

                ImageCard(
                    photos = photos[index]!!,
                    title = "Road from Chitterwach to Imamsahib"
                )
            }

        }
        Box(
            contentAlignment = Center,
            modifier = Modifier.fillMaxSize()
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = MaterialTheme.colors.primary)
            }
            if (loadError.isNotEmpty()) {
                RetrySection(error = loadError) {
                    //   val context = LocalContext.current
                }
            }
        }

    }
}


@OptIn(ExperimentalCoilApi::class)
@Composable
fun ImageCard(
    modifier: Modifier = Modifier,
    photos: Photo,
    contentDescription: String = "Image",
    title: String? = null,
) {
    val painter = rememberImagePainter(
        data = photos.src.original,
        builder = {
            crossfade(true)
        }
    )

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = 8.dp
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
        ) {
            Image(
                painter = painter,
                modifier = modifier,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop
            )
            when (painter.state) {
                is ImagePainter.State.Loading -> {
                    // Display a circular progress indicator whilst loading
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
                is ImagePainter.State.Error -> {

                }
                ImagePainter.State.Empty -> {

                }
                is ImagePainter.State.Success -> {

                }
            }
            photos.photographer.let {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black
                                ), startY = 300f
                            )
                        )
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {

                    Text(
                        photos.photographer,
                        style = TextStyle(color = Color.White, fontSize = 16.sp)
                    )
                }


            }

        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun RetrySection(
    error: String,
    onRetry: () -> Unit
) {
    Column {
        Text(error, color = Color.Red, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { onRetry() },
            modifier = Modifier.align(CenterHorizontally)
        ) {
            Text(text = "Retry")
        }
    }
}

@Composable
fun showProgress() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            color = Color.Black,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}