package com.mercadolibre.melitest.core

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    private val _events = MutableSharedFlow<GeneralEvent>()
    val event = _events.asSharedFlow()

    protected suspend fun sendEvent(event: GeneralEvent) {
        _events.emit(event)
    }

    fun BaseViewModel.executeSuspending(
        onError: ((Throwable) -> Unit)? = null,
        block: suspend () -> Unit
    ) {
        viewModelScope.launch {
            try {
                block()
            } catch (e: Exception) {
                onError?.invoke(e)
                Log.e("BaseViewModel execute suspend", e.message ?: "Error")
            }
        }
    }
}
