package com.mercadolibre.melitest.core

sealed class ScreenState {
    data object Loading : ScreenState()
    data object Error : ScreenState()
    data object Success : ScreenState()
}
