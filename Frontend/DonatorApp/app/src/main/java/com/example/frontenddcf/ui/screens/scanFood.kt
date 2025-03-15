package com.example.frontenddcf.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.frontenddcf.R


@Composable
fun ScanFoodScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.8f))
    ) {
        // Camera preview / food image background
        AsyncImage(
            model = R.drawable.food,
            contentDescription = "Food Preview",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alpha = 0.8f
        )

        // Scan frame overlay with corners
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            // Scanner frame corners
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .aspectRatio(1f)
            ) {
                // Top-left corner
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.Transparent)
                        .align(Alignment.TopStart)
                ) {
                    Box(modifier = Modifier
                        .width(4.dp)
                        .height(20.dp)
                        .background(Color.White)
                        .align(Alignment.TopStart)
                    )
                    Box(modifier = Modifier
                        .width(20.dp)
                        .height(4.dp)
                        .background(Color.White)
                        .align(Alignment.TopStart)
                    )
                }

                // Top-right corner
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.Transparent)
                        .align(Alignment.TopEnd)
                ) {
                    Box(modifier = Modifier
                        .width(4.dp)
                        .height(20.dp)
                        .background(Color.White)
                        .align(Alignment.TopEnd)
                    )
                    Box(modifier = Modifier
                        .width(20.dp)
                        .height(4.dp)
                        .background(Color.White)
                        .align(Alignment.TopEnd)
                    )
                }

                // Bottom-left corner
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.Transparent)
                        .align(Alignment.BottomStart)
                ) {
                    Box(modifier = Modifier
                        .width(4.dp)
                        .height(20.dp)
                        .background(Color.White)
                        .align(Alignment.BottomStart)
                    )
                    Box(modifier = Modifier
                        .width(20.dp)
                        .height(4.dp)
                        .background(Color.White)
                        .align(Alignment.BottomStart)
                    )
                }

                // Bottom-right corner
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.Transparent)
                        .align(Alignment.BottomEnd)
                ) {
                    Box(modifier = Modifier
                        .width(4.dp)
                        .height(20.dp)
                        .background(Color.White)
                        .align(Alignment.BottomEnd)
                    )
                    Box(modifier = Modifier
                        .width(20.dp)
                        .height(4.dp)
                        .background(Color.White)
                        .align(Alignment.BottomEnd)
                    )
                }
            }
        }

        // Scan button or action prompt
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 64.dp)
        ) {
            Button(
                onClick = { navController.navigate("scan_food2")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(56.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFAA33)
                )
            ) {
                Text(
                    text = "Identifier les aliments",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}