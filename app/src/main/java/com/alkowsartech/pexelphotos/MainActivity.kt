package com.alkowsartech.pexelphotos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alkowsartech.pexelphotos.ui.photolistscreen.PhotoListScreen
import com.alkowsartech.pexelphotos.ui.theme.PexelPhotosTheme
import com.alkowsartech.pexelphotos.viewmodel.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  viewModel.fetchPhotos()
        setContent {
            PexelPhotosTheme {
                Column() {
                    TopAppBar(
                        title = { Text(text = "Photos") },
                        backgroundColor = MaterialTheme.colors.background
                    )
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "photo_list_screen"
                    ) {
                        composable("photo_list_screen") {
                            PhotoListScreen(navController = navController, viewModel)
                        }
                    }
                }
            }
        }
    }
}
