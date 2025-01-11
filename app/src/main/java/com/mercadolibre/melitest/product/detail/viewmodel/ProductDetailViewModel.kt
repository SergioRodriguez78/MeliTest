package com.mercadolibre.melitest.product.detail.viewmodel

import android.util.Log
import com.mercadolibre.melitest.core.BaseViewModel
import com.mercadolibre.melitest.core.ScreenState
import com.mercadolibre.melitest.notification.NotificationManager
import com.mercadolibre.melitest.product.data.ProductRepository
import com.mercadolibre.melitest.product.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProductDetailViewModel(
    private val productRepository: ProductRepository,
    private val notificationManager: NotificationManager
) : BaseViewModel() {

    private var _selectedProduct: MutableStateFlow<Product?> = MutableStateFlow(null)
    val selectedProduct = _selectedProduct.asStateFlow()

    private val _screenState = MutableStateFlow<ScreenState>(ScreenState.Loading)
    val screenState = _screenState.asStateFlow()

    fun onProductDetail(productId: String) {
        executeSuspending(
            onError = {
                notificationManager.showError("Error inesperado")
                _screenState.update { ScreenState.Error }
            }
        ) {
            val product = productRepository.getProductDetail(productId)
            product
                .onSuccess { productDetail ->
                    Log.i("ProductDetailViewModel", "Product: $productDetail")
                    _selectedProduct.update { productDetail }
                    _screenState.update { ScreenState.Success }
                }.onFailure { error ->
                    notificationManager.showError(error.message ?: "Error inesperado")
                    _screenState.update { ScreenState.Error }
                }
        }
    }
}
