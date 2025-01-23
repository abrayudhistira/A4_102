package abrayudhistira.cobafinal.ui.navigasi

import abrayudhistira.cobafinal.R
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            //.padding(bottom = 8.dp) // Optional, for better visual effect
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)), // Rounded corners at the top
        containerColor = Color.Black // Menetapkan warna latar belakang menjadi hitam
    ) {
        val items = listOf(
            DestinasiHomeJenisProperty,
            DestinasiMainScreen, // Main will be in the center
            DestinasiHomePemilik,
        )

        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination

        // Add items to the left, center, and right
        items.forEachIndexed { index, destination ->
            // If it's the center item (DestinasiMainScreen), apply different layout
            if (index == 1) { // Center item at index 2
                // Center item (Main)
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.home), // Update with your center icon
                            contentDescription = "Main"
                        )
                    },
                    selected = currentDestination?.route == destination.route,
                    onClick = {
                        if (currentDestination?.route != destination.route) {
                            navController.navigate(destination.route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .weight(1f) // This makes the center item occupy more space
                )
            } else {
                // For left and right items
                NavigationBarItem(
                    icon = {
                        when (destination) {
                            DestinasiHomeProperty -> Icon(painter = painterResource(id = R.drawable.property), contentDescription = "Properti")
                            DestinasiHomeJenisProperty -> Icon(painter = painterResource(id = R.drawable.jenisproperty), contentDescription = "Jenis Properti")
                            DestinasiHomePemilik -> Icon(painter = painterResource(id = R.drawable.owner), contentDescription = "Pemilik")
//                            DestinasiHomeBuku -> Icon(painter = painterResource(id = R.drawable.bukuputih), contentDescription = "Buku")
//                            DestinasiHomeKategori -> Icon(painter = painterResource(id = R.drawable.kategoriputih), contentDescription = "Kategori")
//                            DestinasiHomePenulis -> Icon(painter = painterResource(id = R.drawable.penulisputih), contentDescription = "Penulis")
//                            DestinasiHomePenerbit -> Icon(painter = painterResource(id = R.drawable.penerbitputih), contentDescription = "Penerbit")
                        }
                    },
                    selected = currentDestination?.route == destination.route,
                    onClick = {
                        if (currentDestination?.route != destination.route) {
                            navController.navigate(destination.route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .weight(1f) // Make left and right items equally spaced
                )
            }
        }
    }
}