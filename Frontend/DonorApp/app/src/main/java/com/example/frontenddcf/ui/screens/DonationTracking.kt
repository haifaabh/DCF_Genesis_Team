package com.example.frontenddcf.ui.screens


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import android.graphics.Color as AndroidColor

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import kotlinx.coroutines.launch
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.frontenddcf.R
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.delay
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Polyline
import java.net.URL





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DonationTracking(navController: NavHostController) {
    Box(
        modifier = Modifier.fillMaxSize()
            .padding(top = 16.dp)
    ) {
        // La carte (en arrière-plan, prenant tout l'écran)
        val context = LocalContext.current
        val fusedLocationProviderClient = remember { LocationServices.getFusedLocationProviderClient(context) }
        val coroutineScope = rememberCoroutineScope()
        var userLocation by remember { mutableStateOf<GeoPoint?>(null) }
        val currentUserLocation = rememberUpdatedState(userLocation)
        var mapView by remember { mutableStateOf<MapView?>(null) }
        val startPoint = GeoPoint(36.744138113208315, 4.369579979473619)

        // Obtenir la localisation actuelle
        LaunchedEffect(Unit) {
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
                    if (location != null) {
                        userLocation = GeoPoint(location.latitude, location.longitude)
                        Log.d("MapTest", "Position actuelle récupérée : ${userLocation?.latitude}, ${userLocation?.longitude}")
                    } else {
                        // Si la position est nulle, essayer d'obtenir une mise à jour active
                        fusedLocationProviderClient.requestLocationUpdates(
                            LocationRequest.create().apply {
                                interval = 10000 // Intervalle de 10 secondes
                                fastestInterval = 5000 // Intervalle minimum de 5 secondes
                                priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                            },
                            object : LocationCallback() {
                                override fun onLocationResult(locationResult: LocationResult) {
                                    val location = locationResult.lastLocation
                                    if (location != null) {
                                        userLocation = GeoPoint(location.latitude, location.longitude)
                                        Log.d("Map", "Position mise à jour : ${userLocation?.latitude}, ${userLocation?.longitude}")
                                    }
                                }
                            },
                            Looper.getMainLooper()
                        )
                    }
                }
            } else {
                Log.e("Map", "Permission de localisation non accordée.")
            }
        }

        // La carte (en arrière-plan, prenant tout l'écran)
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { ctx ->
                MapView(ctx).apply {
                    Configuration.getInstance().userAgentValue = "com.example.frontendtdm"
                    setTileSource(TileSourceFactory.MAPNIK)
                    setBuiltInZoomControls(true)
                    setMultiTouchControls(true)
                    mapView = this // Assigner à l'état
                    controller.setZoom(8.0)

                    // Ajouter un marqueur pour la position de départ
                    val startMarker = Marker(this).apply {
                        position = startPoint
                        setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                        icon = BitmapDrawable(
                            context.resources,
                            Bitmap.createScaledBitmap(
                                BitmapFactory.decodeResource(context.resources, R.drawable.livreur),
                                50, 50, true
                            )
                        )
                        title = "Départ"
                    }
                    overlays.add(startMarker)

                    // Centrer la carte sur la position de départ initialement
                    controller.setCenter(startPoint)
                }
            },
            update = { mapView ->
                currentUserLocation.value?.let { location ->
                    Log.d("MapDagi", "Position actuelle mise à jour : ${location.latitude}, ${location.longitude}")

                    // Ajouter un marqueur pour la position actuelle
                    val endMarker = Marker(mapView).apply {
                        position = location
                        setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                        icon = BitmapDrawable(
                            context.resources,
                            Bitmap.createScaledBitmap(
                                BitmapFactory.decodeResource(context.resources, R.drawable.maison),
                                50, 50, true
                            )
                        )
                        title = "Arrivée"
                    }
                    mapView.overlays.add(endMarker)

                    // Charger l'itinéraire depuis OSRM
                    val routeUrl =
                        "https://router.project-osrm.org/route/v1/driving/${startPoint.longitude},${startPoint.latitude};${location.longitude},${location.latitude}?overview=full&geometries=geojson"

                    coroutineScope.launch(Dispatchers.IO) {
                        try {
                            val result = fetchRoute(routeUrl) // Implémentez cette méthode pour récupérer les points de l'itinéraire
                            if (result != null) {
                                val routeOverlay = Polyline().apply {
                                    outlinePaint.strokeWidth = 20f
                                    outlinePaint.color = AndroidColor.parseColor("#FEBC12")
                                    setPoints(result)
                                }

                                withContext(Dispatchers.Main) {
                                    mapView.overlays.add(routeOverlay)
                                    mapView.invalidate()
                                }
                            }
                        } catch (e: Exception) {
                            Log.e("OSMDroid", "Erreur lors de la récupération de l'itinéraire : $e")
                        }
                    }

                    // Mettre à jour la position sur la carte
                    mapView.controller.setCenter(location)
                    mapView.invalidate() // Rafraîchir la carte
                }
            }
        )

        // La barre du haut (au-dessus de la carte)
        Box(
            modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(40.dp))
                .padding(horizontal = 6.dp, vertical = 8.dp)
                .align(Alignment.TopCenter), // Placer en haut au centre
            contentAlignment = Alignment.Center,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .size(height = 40.dp, width = 320.dp)
            ) {
                // Bouton de retour
                IconButton(
                    onClick = {
                        navController.navigate("home") // Revenir à l'écran d'accueil
                    },
                    modifier = Modifier
                        .size(40.dp) // Taille du bouton de l'icône
                        .background(
                            Color(0xFFFEBC12),
                            CircleShape
                        ) // Cercle orange pour l'icône
                        .border(1.dp, Color(0xFFFEBC12), CircleShape) // Contour jaune
                        .padding(5.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft, // Icône de retour
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }

                // Titre centré
                Text(
                    text = "Suivi des dons",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black,
                    modifier = Modifier.weight(1f), // Espace égal de chaque côté
                    textAlign = TextAlign.Center
                )
            }
        }

        // Bouton pour confirmer la bonne livraison du don (en bas de l'écran)
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp)
        ) {
            Button(
                onClick = { navController.navigate("location") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFEBC12)),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "Confirmer mon don",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }
}



// Fonction pour récupérer les points de l'itinéraire
suspend fun fetchRoute(routeUrl: String): List<GeoPoint>? {
    return try {
        val response = withContext(Dispatchers.IO) {
            URL(routeUrl).openStream().bufferedReader().use { it.readText() }
        }
        val jsonResponse = JSONObject(response)
        val routes = jsonResponse.getJSONArray("routes")
        if (routes.length() > 0) {
            val geometry = routes.getJSONObject(0).getJSONObject("geometry")
            val coordinates = geometry.getJSONArray("coordinates")
            val points = mutableListOf<GeoPoint>()
            for (i in 0 until coordinates.length()) {
                val coordinate = coordinates.getJSONArray(i)
                points.add(GeoPoint(coordinate.getDouble(1), coordinate.getDouble(0)))
            }
            points
        } else {
            null
        }
    } catch (e: Exception) {
        Log.e("fetchRoute", "Erreur : ${e.message}")
        null
    }
}
