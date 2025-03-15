package com.example.frontenddcf.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.frontenddcf.R


@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(44.dp))
        // Top greeting with user profile
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(top=16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Hi Amina,",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "ready to donate today?",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }

            // User profile image
            Image(
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Insights Section
        Text(
            text = "Your Insights",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            InsightCard(
                icon = R.drawable.scan,
                title = "Scan &\nAdd Food",
                backgroundColor = Color(0xFFF0F0FF),
                iconTint = Color(0xFF6366F1),
                modifier = Modifier.weight(1f),
                onClick = { navController.navigate("scan_food") }
            )

            InsightCard(
                icon = R.drawable.locationmap,
                title = "Live Food\nCollection Points",
                backgroundColor = Color(0xFFFFF0EB),
                iconTint = Color(0xFFFF7A50),
                modifier = Modifier.weight(1f),
                onClick = { navController.navigate("location") }
            )
        }

        Spacer(modifier = Modifier.height(44.dp))

        // Donations section
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Total Donations",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Text(
            text = "You helped 8 families this week!",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // Weekly donation chart
        WeeklyDonationChart(
            modifier = Modifier
                .fillMaxWidth()
                .height(190.dp)
                .padding(vertical = 10.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        // Bottom navigation bar
        BottomNavigationBar(navController = navController)
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomNavItem(
            icon = R.drawable.maison,
            isSelected = true,
            onClick = { /* Already on home */ }
        )
        BottomNavItem(
            icon = R.drawable.commande,
            isSelected = false,
            onClick = { navController.navigate("notifications") }
        )
        BottomNavItem(
            icon = R.drawable.scan,
            isSelected = false,
            onClick = { navController.navigate("scan_food") }
        )
        BottomNavItem(
            icon = R.drawable.commande,
            isSelected = false,
            onClick = { navController.navigate("history") }
        )
        BottomNavItem(
            icon = R.drawable.livraison,
            isSelected = false,
            onClick = { navController.navigate("cart") }
        )
    }
}

@Composable
fun BottomNavItem(
    icon: Int,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(if (isSelected) Color(0xFFEBF8FF) else Color.Transparent)
            .padding(8.dp)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = if (isSelected) Color(0xFF3B82F6) else Color.Gray,
            modifier = Modifier.size(24.dp)
        )
    }
}


@Composable
fun InsightCard(
    icon: Int,
    title: String,
    backgroundColor: Color,
    iconTint: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Icon
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White.copy(alpha = 0.6f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(24.dp)
                )
            }

            // Title
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                lineHeight = 22.sp
            )
        }
    }
}

@Composable
fun WeeklyDonationChart(
    modifier: Modifier = Modifier
) {
    // Sample data for the weekly donations
    val donationData = listOf(
        DonationDay("Mon", 0.4f, false),
        DonationDay("Tue", 0.6f, false),
        DonationDay("Wed", 0.3f, false),
        DonationDay("Thu", 0.7f, false),
        DonationDay("Fri", 0.5f, false),
        DonationDay("Sat", 0.8f, true),  // Highlighted day
        DonationDay("Sun", 0.6f, false)
    )

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        donationData.forEach { day ->
            DonationBar(
                dayName = day.name,
                donationLevel = day.level,
                isHighlighted = day.isHighlighted,
                modifier = Modifier.weight(8f)
            )
        }
    }
}

@Composable
fun DonationBar(
    dayName: String,
    donationLevel: Float,  // 0.0 to 1.0 representing donation level
    isHighlighted: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.height(220.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight(0.8f)
                .width(16.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            // Background bar
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(16.dp)
                    .padding(vertical = 8.dp)
            ) {
                Canvas(
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Draw the background track
                    drawRoundRect(
                        color = Color(0xFFEEEEEE),
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(4.dp.toPx())
                    )
                }
            }

            // Filled donation bar
            Box(
                modifier = Modifier
                    .fillMaxHeight(donationLevel)
                    .width(16.dp)
                    .padding(vertical = 8.dp)
            ) {
                Canvas(
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Draw the donation level
                    drawRoundRect(
                        color = if (isHighlighted) Color(0xFFFFC107) else Color(0xFF1E293B),
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(4.dp.toPx())
                    )
                }
            }
        }

        // Day name at the bottom
        Text(
            text = dayName,
            fontSize = 12.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

// Data class to hold donation information for each day
data class DonationDay(
    val name: String,
    val level: Float,  // 0.0 to 1.0
    val isHighlighted: Boolean
)