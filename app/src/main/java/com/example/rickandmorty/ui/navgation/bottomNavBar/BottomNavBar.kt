package com.example.rickandmorty.ui.navgation.bottomNavBar

 import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
 import androidx.navigation.NavController
 import androidx.navigation.compose.currentBackStackEntryAsState
 import com.example.rickandmorty.ui.navgation.NavScreens
 import kotlin.collections.listOf

@Composable
fun BottomNavBar(navController: NavController) {

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar {
        getBottomNavItems().forEach {item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route){
                        popUpTo(navController.graph.startDestinationId){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = "navItem"
                    )
                },
                label = {
                    Text(
                        text = item.title
                    )
                }
            )
        }
    }

}

private fun getBottomNavItems() = listOf(
    BottomNavItem(
        route = NavScreens.Characters.route,
        title = "Characters",
        icon = Icons.Filled.Face,
    ),
    BottomNavItem(
        route = NavScreens.Locations.route,
        title = "Locations",
        icon = Icons.Filled.LocationOn,
    ),
    BottomNavItem(
        route = NavScreens.Episodes.route,
        title = "Episodes",
        icon = Icons.Filled.Menu,
    ),
    BottomNavItem(
        route = NavScreens.Favorites.route,
        title = "Favorites",
        icon = Icons.Filled.Menu,
    ),
)
