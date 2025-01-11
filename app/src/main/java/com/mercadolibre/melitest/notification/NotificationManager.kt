package com.mercadolibre.melitest.notification

import androidx.compose.material3.SnackbarHostState

interface NotificationManager {

    val snackbarHostState: SnackbarHostState

    fun showError(message: String)
}
