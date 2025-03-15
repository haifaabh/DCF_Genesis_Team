package com.example.frontenddcf.navigation


import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.frontenddcf.Greeting
import com.example.frontenddcf.ui.screens.AddFoodScreen
import com.example.frontenddcf.ui.screens.AddNewItemsScreen
import com.example.frontenddcf.ui.screens.DonationTracking
import com.example.frontenddcf.ui.screens.HomeScreen
import com.example.frontenddcf.ui.screens.ScanFoodScreen
import com.example.frontenddcf.ui.screens.SuccessDonation


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavigation(navController: NavHostController) {
    // Initialisation des ViewModels

    // Définition des routes dans NavHost
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController)
        }

        composable("scan_food1") {
            AddNewItemsScreen(navController)
        }

        composable("scan_food") {
            ScanFoodScreen(navController)
        }

        composable("scan_food2") {
            AddFoodScreen(navController)
        }

        composable("success") {
            SuccessDonation(navController)
        }

        composable("location") {
            DonationTracking(navController)
        }

    }
}