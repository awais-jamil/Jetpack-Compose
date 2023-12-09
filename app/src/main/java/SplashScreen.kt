import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.awais.android.presentation.viewmodels.AuthViewModel

@Composable
fun SplashScreen(
    viewModel: AuthViewModel,
    navigateToLogin: () -> Unit,
    navigateToHome: () -> Unit,
) {
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()
    val isAuthenticationLoaded by viewModel.isAuthenticationLoaded.collectAsState()
    
    LaunchedEffect(key1 = true) {
        // Check user authentication status when the screen launches
        viewModel.getCurrentUser()
    }
    
    if (isAuthenticationLoaded) {
        // Navigate based on the authentication status after loading has finished
        if (isLoggedIn) {
            navigateToHome()
        } else {
            navigateToLogin()
        }
    }
    // You can show a loading indicator or an initial splash screen content while the authentication status is being loaded
    // For example:
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
    
}
