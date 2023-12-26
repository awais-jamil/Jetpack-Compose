package com.awais.android.features.home.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.awais.android.features.auth.presentation.viewmodels.AuthViewModel
import com.awais.android.features.friends.presentation.screens.FriendsScreen
import com.awais.android.features.news.presentation.screens.NewsScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    authViewModel: AuthViewModel,
    onLogout: () -> Unit,
) {
    var currentScreen by remember { mutableStateOf(Screen.News) }
    
    Scaffold(
        topBar = {
            MyAppBar(
                title = currentScreen.title,
                onLogout = {
                    authViewModel.logout()
                    onLogout()
                }
            )
        },
        bottomBar = {
            BottomNavigationBar(
                onScreenSelected = { screen -> currentScreen = screen },
            )
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier.padding(
                    PaddingValues(
                        0.dp,
                        innerPadding.calculateTopPadding(),
                        0.dp,
                        innerPadding.calculateBottomPadding()
                    )
                )
            ) {
                when (currentScreen) {
                    Screen.News -> NewsScreen()
                    Screen.Friends -> FriendsScreen(hiltViewModel())
                    else -> {
                        Text("404 page not found")
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppBar(
    title: String,
    onLogout: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge.copy(
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold,
                ),
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
        ),
        actions = {
            IconButton(onClick = { onLogout() }) {
                Icon(
                    Icons.Filled.ExitToApp,
                    contentDescription = "logout",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    )
}

@Composable
fun BottomNavigationBar(onScreenSelected: (Screen) -> Unit) {
    val items = listOf(
        Screen.News,
        Screen.Friends,
    )
    var selectedItem by remember { mutableIntStateOf(0) }
    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                alwaysShowLabel = true,
                icon = {
                    Icon(
                        item.icon,
                        contentDescription = item.title,
                        tint = MaterialTheme.colorScheme.primary,
                    )
                },
                label = {
                    Text(
                        item.title,
                        style = MaterialTheme.typography.labelLarge.copy(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                        ),
                    )
                },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    onScreenSelected(item)
                }
            )
        }
    }
}

enum class Screen(val title: String, val icon: ImageVector) {
    News("News", Icons.Filled.MailOutline),
    Friends("Friends", Icons.Filled.List),
}
