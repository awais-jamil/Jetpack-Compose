package com.awais.android

import SplashScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.awais.android.presentation.screens.HomeScreen
import com.awais.android.presentation.screens.LoginScreen
import com.awais.android.presentation.viewmodels.AuthViewModel
import com.awais.android.theme.JetpackProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            JetpackProjectTheme {
                val viewModel = AuthViewModel()
                val navController = rememberNavController()
                val navActions = remember(navController) { NavActions(navController) }
                NavHost(navController = navController, startDestination = "splash") {
                    addSplashScreen(actions = navActions, authViewModel = viewModel)
                    addLoginScreen(actions = navActions, authViewModel = viewModel)
                    addHomeScreen(actions = navActions)
                }
            }
        }
    }
}


class NavActions(navController: NavHostController) {
    val navigateToLogin: () -> Unit = {
        navController.navigate("login")
    }
    
    val navigateToHome: () -> Unit = {
        navController.navigate("home")
    }
}

private fun NavGraphBuilder.addSplashScreen(actions: NavActions, authViewModel: AuthViewModel) {
    composable("splash") {
        SplashScreen(
            viewModel = authViewModel,
            navigateToLogin = { actions.navigateToLogin() },
            navigateToHome = { actions.navigateToHome() }
        )
    }
}

private fun NavGraphBuilder.addLoginScreen(actions: NavActions, authViewModel: AuthViewModel) {
    composable("login") {
        LoginScreen(viewModel = authViewModel, navigateToHome = { actions.navigateToHome })
    }
}

private fun NavGraphBuilder.addHomeScreen(actions: NavActions) {
    composable("home") {
        HomeScreen()
    }
}