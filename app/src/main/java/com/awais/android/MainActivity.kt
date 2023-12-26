package com.awais.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.awais.android.features.auth.presentation.screens.LoginScreen
import com.awais.android.features.auth.presentation.screens.SignUpScreen
import com.awais.android.features.auth.presentation.viewmodels.AuthViewModel
import com.awais.android.features.friends.presentation.viewmodels.FriendsViewModel
import com.awais.android.features.home.presentation.screens.HomeScreen
import com.awais.android.features.splash.SplashScreen
import com.awais.android.theme.JetpackProjectTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            JetpackProjectTheme {
                val viewModel = hiltViewModel<AuthViewModel>()
                val friendsViewModel = hiltViewModel<FriendsViewModel>()
                val navController = rememberNavController()
                val navActions = remember(navController) { NavActions(navController) }
                NavHost(navController = navController, startDestination = "splash") {
                    addSplashScreen(actions = navActions, authViewModel = viewModel)
                    addLoginScreen(actions = navActions, authViewModel = viewModel)
                    addSignupScreen(actions = navActions, authViewModel = viewModel)
                    addHomeScreen(
                        actions = navActions,
                        authViewModel = viewModel,
                    )
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
    
    val navigateToSignUp: () -> Unit = {
        navController.navigate("signup")
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
        LoginScreen(
            viewModel = authViewModel,
            navigateToHome = { actions.navigateToHome() },
            navigateToSignUp = { actions.navigateToSignUp() },
        )
    }
}

private fun NavGraphBuilder.addSignupScreen(actions: NavActions, authViewModel: AuthViewModel) {
    composable("signup") {
        SignUpScreen(
            viewModel = authViewModel,
            navigateToHome = { actions.navigateToHome() },
            navigateToLogin = { actions.navigateToLogin() },
        )
    }
}

private fun NavGraphBuilder.addHomeScreen(
    actions: NavActions,
    authViewModel: AuthViewModel,
) {
    composable("home") {
        HomeScreen(
            authViewModel = authViewModel,
            onLogout = { actions.navigateToLogin() },
        )
    }
}