package com.example.frontenddcf.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.navigation.NavHostController
import com.example.frontenddcf.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFoodScreen(navController: NavHostController) {
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add food", fontWeight = FontWeight.Medium) },
                navigationIcon = {
                    IconButton(onClick = { /* Navigate back */ }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                )
            )
        }
    ) {
        paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .background(Color(0xFFF9F9F9))
                .verticalScroll(scrollState)
        ) {
            Text(
                text = "Items identified",
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            // Food items
            FoodItem(
                name = "Fried Chicken Pieces",
                portions = 2,
                isFresh = true
            )

            FoodItem(
                name = "Spiced Rice (Pilaf/Biryani style)",
                portions = 2,
                isFresh = true
            )

            FoodItem(
                name = "Salad (Mixed vegetables)",
                portions = 2,
                isFresh = true
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Packaging suggestions
            Text(
                text = "Packaging Suggestions",
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Main Course
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Main Course",
                    fontWeight = FontWeight.Medium
                )

                Text(
                    text = "*Separate Packaging",
                    color = Color.Red,
                    fontSize = 12.sp
                )
            }

            // Main course packaging options
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = { },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFFFFAA00)
                    ),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = androidx.compose.ui.graphics.SolidColor(Color(0xFFFFAA00))
                    )
                ) {
                    Text("Fried Chicken Pieces", color = Color(0xFFFFAA00))
                }

                OutlinedButton(
                    onClick = { },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFFFFAA00)
                    ),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = androidx.compose.ui.graphics.SolidColor(Color(0xFFFFAA00))
                    )
                ) {
                    Text("Spiced Rice", color = Color(0xFFFFAA00))
                }
            }

            // Packaging suggestion for main course
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .border(1.dp, Color(0xFFE91E63), RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                Text(
                    "use a single container but ensure the chicken doesn't directly sit on the rice (use a piece of parchment paper as a barrier)",
                    color = Color(0xFF333333)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Side Dish section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Side Dish Combination",
                    fontWeight = FontWeight.Medium
                )

                Text(
                    text = "*Single Packaging",
                    color = Color.Green,
                    fontSize = 12.sp
                )
            }

            // Side dish packaging options
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = { },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFF0077CC)
                    ),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = androidx.compose.ui.graphics.SolidColor(Color(0xFF0077CC))
                    )
                ) {
                    Text("Fried Appetizers", color = Color(0xFF0077CC))
                }

                OutlinedButton(
                    onClick = { },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFF0077CC)
                    ),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = androidx.compose.ui.graphics.SolidColor(Color(0xFF0077CC))
                    )
                ) {
                    Text("Fried Potatoes", color = Color(0xFF0077CC))
                }
            }

            // Suggestion for side dish
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .border(1.dp, Color(0xFFE91E63), RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                Text(
                    "Place paper towels at the bottom of the container to absorb excess oil and maintain crispiness as much as possible.",
                    color = Color(0xFF333333)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Bottom buttons
            Button(
                onClick = { navController.navigate("scan_food1")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFFFFAA00)
                ),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    brush = androidx.compose.ui.graphics.SolidColor(Color(0xFFFFAA00))
                )
            ) {
                Text("Add Item", fontWeight = FontWeight.Medium)
            }

            Button(
                onClick = { navController.navigate("success")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFAA00),
                    contentColor = Color.White
                )
            ) {
                Text("Add donation", fontWeight = FontWeight.Medium)
            }
        }
    }
}

@Composable
fun FoodItem(name: String, portions: Int, isFresh: Boolean) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFE)),
        shape = RoundedCornerShape(16.dp),
        border = ButtonDefaults.outlinedButtonBorder.copy(
                brush = androidx.compose.ui.graphics.SolidColor(Color(0xFFFEEEEE))
                )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Image
            Image(
                painter = painterResource(R.drawable.food), // Replace with actual food image
                contentDescription = name,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(6.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Food details
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = name,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )

                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Portions button
                    OutlinedButton(
                        onClick = { },
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 1.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color(0xFFFFAA00)
                        ),
                        border = ButtonDefaults.outlinedButtonBorder.copy(
                            brush = androidx.compose.ui.graphics.SolidColor(Color(0xFFFFAA00))
                        )
                    ) {
                        Text("$portions Portions", fontSize = 12.sp, color = Color(0xFFFFAA00))
                    }

                    // Fresh button
                    Button(
                        onClick = { },
                        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 1.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4CAF50),
                            contentColor = Color.White
                        )
                    ) {
                        Text("Still fresh", fontSize = 12.sp)
                    }
                }
            }
        }
    }
}