package com.mercadolibre.melitest.notification

import androidx.compose.material3.SnackbarHostState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class NotificationManagerImpl: NotificationManager {

    private val notificationScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    override val snackbarHostState = SnackbarHostState()

    override fun showError(message: String) {
        notificationScope.launch {
            snackbarHostState.showSnackbar(
                message = message,
                withDismissAction = true
            )
        }
    }
}
