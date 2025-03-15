package com.example.frontenddcf.ui.screens

import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.frontenddcf.R

data class Ingredient(
    val name: String,
    val iconId: Int,
    val backgroundColor: androidx.compose.ui.graphics.Color
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewItemsScreen(navController: NavController) {
    var itemName by remember { mutableStateOf("Chorba Frik") }
    var detailsText by remember { mutableStateOf("Lorem ipsum Dolor Sit Amet, Consectetur Adips Cing Elit. Bibendum In Vel, Mattis Et Amet Dui Mauris Turpis.") }
    val context = LocalContext.current

    // Initialiser l'imageUri à null pour commencer
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val sdkVersion = Build.VERSION.SDK_INT

    // Vérifier les permissions en fonction de la version Android
    val hasPermission = when {
        sdkVersion >= Build.VERSION_CODES.TIRAMISU -> {
            // Pour Android 13 et au-delà, utiliser READ_MEDIA_IMAGES
            ContextCompat.checkSelfPermission(
                context, android.Manifest.permission.READ_MEDIA_IMAGES
            ) == PackageManager.PERMISSION_GRANTED
        }
        sdkVersion >= Build.VERSION_CODES.M -> {
            // Pour Android 6.0 à 12, utiliser READ_EXTERNAL_STORAGE
            ContextCompat.checkSelfPermission(
                context, android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        }
        else -> true // Pour les versions antérieures, on suppose que la permission est accordée
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                // Permission accordée, vous pouvez maintenant ouvrir le sélecteur d'images
            } else {
                // Permission refusée
                Toast.makeText(context, "Permission manquante pour accéder aux images", Toast.LENGTH_SHORT).show()
            }
        }
    )

    // Lancez le sélecteur d'images si la permission est accordée
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            if (uri != null) {
                imageUri = uri // Mettez à jour l'URI de l'image
            }
        }
    )

    // Si la permission n'est pas accordée, demandez-la
    if (!hasPermission) {
        LaunchedEffect(Unit) {
            when {
                sdkVersion >= Build.VERSION_CODES.TIRAMISU -> {
                    permissionLauncher.launch(android.Manifest.permission.READ_MEDIA_IMAGES)
                }
                else -> {
                    permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        // Top app bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Back button
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF5F5F5))
                    .clickable { navController.popBackStack() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = androidx.compose.ui.graphics.Color.Black
                )
            }

            Text(
                text = "Ajouter un aliment",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = "Reset",
                fontSize = 16.sp,
                color = Color(0xFFFF5722),
                modifier = Modifier.clickable { /* Reset form */ }
            )
        }

        // Main form content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            // Item name field
            Text(
                text = "Item Name",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = itemName,
                onValueChange = { itemName = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color(0xFFE0E0E0),
                    focusedBorderColor = Color(0xFF3B82F6)
                )
            )

            // Upload photo section
            Text(
                text = "Upload Photo/Video",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Uploaded image
                Image(
                    painter = painterResource(id = R.drawable.food),
                    contentDescription = "Food Photo",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )

                // Add button
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .border(
                            width = 1.dp,
                            color = Color(0xFFE0E0E0),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clickable {
                            if (hasPermission) {
                                launcher.launch("image/*") // Ouvrir le sélecteur d'images
                            } else {
                                when {
                                    sdkVersion >= Build.VERSION_CODES.TIRAMISU -> {
                                        permissionLauncher.launch(android.Manifest.permission.READ_MEDIA_IMAGES)
                                    }
                                    else -> {
                                        permissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                                    }
                                }
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.add),
                            contentDescription = "Upload",
                            tint = Color(0xFF6366F1),
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Add",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                    }
                }
            }

            // Ingredients section
            Text(
                text = "Ingredients",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Basic ingredients
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Basic",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { /* Show all ingredients */ }
                ) {
                    Text(
                        text = "See All",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Show more",
                        tint = Color.Gray
                    )
                }
            }

            // Basic ingredients row
            IngredientsRow(
                ingredients = listOf(
                    Ingredient("Salt", R.drawable.apple, Color(0xFFFFF0EB)),
                    Ingredient("Chicken", R.drawable.apple, Color(0xFFF8F9FA)),
                    Ingredient("Onion", R.drawable.apple, Color(0xFFFFF0EB)),
                    Ingredient("Garlic", R.drawable.apple, Color(0xFFF8F9FA)),
                    Ingredient("Peppers", R.drawable.apple, Color(0xFFFFF0EB)),
                    Ingredient("Ginger", R.drawable.apple, Color(0xFFF8F9FA))
                )
            )

            // Fruit ingredients
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Fruit",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { /* Show all fruits */ }
                ) {
                    Text(
                        text = "See All",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Show more",
                        tint = Color.Gray
                    )
                }
            }

            // Fruit ingredients row
            IngredientsRow(
                ingredients = listOf(
                    Ingredient("Avocado", R.drawable.apple, Color(0xFFF8F9FA)),
                    Ingredient("Apple", R.drawable.apple, Color(0xFFF8F9FA)),
                    Ingredient("Blueberry", R.drawable.apple, Color(0xFFF8F9FA)),
                    Ingredient("Broccoli", R.drawable.apple, Color(0xFFF8F9FA)),
                    Ingredient("Orange", R.drawable.apple, Color(0xFFF8F9FA)),
                    Ingredient("Walnut", R.drawable.apple, Color(0xFFF8F9FA))
                )
            )

            // Details section
            Text(
                text = "Details",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
            )

            OutlinedTextField(
                value = detailsText,
                onValueChange = { detailsText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color(0xFFE0E0E0),
                    focusedBorderColor = Color(0xFF3B82F6)
                )
            )

            Spacer(modifier = Modifier.height(24.dp))
        }

        // Add item button
        Button(
            onClick = { navController.navigate("food_scan2") },
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
                text = "Add Item",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
fun IngredientItem(
    ingredient: Ingredient
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(48.dp)
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(ingredient.backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = ingredient.iconId),
                contentDescription = ingredient.name,
                tint = when (ingredient.name) {
                    "Salt" -> Color(0xFFFF7A50)
                    "Onion" -> Color(0xFFFF7A50)
                    "Peppers" -> Color(0xFFFF7A50)
                    else -> Color.Gray
                },
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = ingredient.name,
            fontSize = 12.sp,
            color = Color.Gray,
            maxLines = 1
        )
    }
}

    @Composable
    fun IngredientsRow(
        ingredients: List<Ingredient>
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ingredients.forEach { ingredient ->
                IngredientItem(ingredient = ingredient)
            }
        }
    }