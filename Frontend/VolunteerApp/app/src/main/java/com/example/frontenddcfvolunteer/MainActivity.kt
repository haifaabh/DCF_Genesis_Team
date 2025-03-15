package com.example.frontenddcfvolunteer

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.frontenddcfvolunteer.navigation.SetupNavigation
import com.example.frontenddcfvolunteer.ui.theme.FrontendDCFTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FrontendDCFTheme {
                val navController = rememberNavController()
                //   register (navController)
                SetupNavigation(navController)
                //LogoutButton(userViewModel,navController)
                //DetailsScreen(navController)
                // SetupNavigation(navController)
                // UserRegistrationForm(userViewModel)
                // register(navController)
                //inscription (navController)

            }
        }
    }
}

@Composable
fun Greeting(navController: NavHostController) {
    Text(
        text = "Hello Dina!"
    )
}
