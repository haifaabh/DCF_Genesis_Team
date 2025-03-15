package com.example.frontenddcf

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.frontenddcf.navigation.SetupNavigation
import com.example.frontenddcf.ui.theme.FrontendDCFTheme

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
