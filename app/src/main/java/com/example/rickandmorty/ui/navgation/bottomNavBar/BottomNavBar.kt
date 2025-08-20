package com.example.rickandmorty.ui.navgation.bottomNavBar

 import androidx.compose.animation.core.animateDpAsState
 import androidx.compose.animation.core.animateFloatAsState
 import androidx.compose.animation.core.tween
 import androidx.compose.foundation.clickable
 import androidx.compose.foundation.interaction.MutableInteractionSource
 import androidx.compose.foundation.interaction.collectIsPressedAsState
 import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
 import androidx.compose.material3.NavigationBarDefaults
 import androidx.compose.material3.NavigationBarItem
 import androidx.compose.material3.NavigationBarItemColors
 import androidx.compose.material3.NavigationBarItemDefaults
 import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
 import androidx.compose.runtime.remember
 import androidx.compose.ui.Modifier
 import androidx.compose.ui.graphics.Color
 import androidx.compose.ui.graphics.graphicsLayer
 import androidx.compose.ui.unit.dp
 import androidx.navigation.NavController
 import androidx.navigation.compose.currentBackStackEntryAsState
 import com.example.rickandmorty.ui.navgation.NavScreens
 import kotlin.collections.listOf

@Composable
fun BottomNavBar(navController: NavController) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar(
        containerColor = Color.DarkGray
    ) {
        getBottomNavItems().forEach {item ->
            val isSelected = currentRoute == item.route

            val scale = animateFloatAsState(
                targetValue = if (isSelected) 1.3f else 1f,
                animationSpec = tween(200),
                label = "iconScale"
            )
            NavigationBarItem(
                modifier = Modifier.graphicsLayer {
                    this.scaleX = scale.value
                    this.scaleY = scale.value
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Black,
                    selectedTextColor = Color.White,
                    unselectedIconColor = Color.LightGray,
                    unselectedTextColor = Color.White,
                    indicatorColor = Color.Green,

                ),
                selected = isSelected,
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = "navItem",

                    )
                },
                onClick = {
                    navController.navigate(item.route){
                        popUpTo(navController.graph.startDestinationId){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
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
