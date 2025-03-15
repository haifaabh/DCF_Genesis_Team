package com.example.frontenddcfneedy.navigation


import FoodRequestScreen
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.frontenddcfneedy.ui.screens.DonationScreen


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavigation(navController: NavHostController) {
    // Initialisation des ViewModels

    // Définition des routes dans NavHost
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            DonationScreen(navController)
        }

        composable("requestfood") {
            FoodRequestScreen(navController)
        }

    }
}