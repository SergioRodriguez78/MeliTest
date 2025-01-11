package com.mercadolibre.melitest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.mercadolibre.melitest.navigation.NavigationController
import com.mercadolibre.melitest.notification.NotificationManager
import com.mercadolibre.melitest.ui.theme.MeliTestTheme
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MeliTestTheme {
                KoinAndroidContext {

                    val navController = rememberNavController()

                    val notificationManager = koinInject<NotificationManager>()

                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        snackbarHost = {
                            SnackbarHost(hostState = notificationManager.snackbarHostState)
                        }
                    ) { innerPadding ->
                        NavigationController(
                            navController = navController,
                            paddingValues = innerPadding
                        )
                    }
                }
            }
        }
    }
}
