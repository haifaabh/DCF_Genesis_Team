package com.example.frontenddcfvolunteer.navigation


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
import com.example.frontenddcfvolunteer.ui.screens.DeliveryDetailsScreen
import com.example.frontenddcfvolunteer.ui.screens.DropOffProcessScreen
import com.example.frontenddcfvolunteer.ui.screens.LivraisonAFaireMap
import com.example.frontenddcfvolunteer.ui.screens.LivraisonTerminee
import com.example.frontenddcfvolunteer.ui.screens.MapLivraison
import com.example.frontenddcfvolunteer.ui.screens.VolunteerHomeScreen


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavigation(navController: NavHostController) {
    // Initialisation des ViewModels

    // Définition des routes dans NavHost
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            VolunteerHomeScreen(navController)
        }

        composable("detaillivraison") {
            DeliveryDetailsScreen(navController)
        }

        composable("voirmap") {
            MapLivraison(navController)
        }

        composable("livraisontodo") {
            LivraisonAFaireMap(navController)
        }

        composable("dropoffprocess") {
            DropOffProcessScreen(navController)
        }

        composable("livraisonfinished") {
            LivraisonTerminee(navController)
        }

    }
}