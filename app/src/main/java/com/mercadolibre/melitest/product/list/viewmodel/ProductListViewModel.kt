package com.mercadolibre.melitest.product.list.viewmodel

import com.mercadolibre.melitest.core.BaseViewModel
import com.mercadolibre.melitest.core.GeneralEvent
import com.mercadolibre.melitest.core.ScreenState
import com.mercadolibre.melitest.navigation.routes.product.ProductRoutes
import com.mercadolibre.melitest.product.data.ProductRepository
import com.mercadolibre.melitest.product.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProductListViewModel(
    private val productRepository: ProductRepository
) : BaseViewModel() {

    private var hasSearched = false

    private var _productList: MutableStateFlow<List<Product>> = MutableStateFlow(emptyList())
    val productList = _productList.asStateFlow()

    private val _screenState = MutableStateFlow<ScreenState>(ScreenState.Loading)
    val screenState = _screenState.asStateFlow()


    fun findProducts(query: String) {
        if (hasSearched) return
        executeSuspending(
            onError = {
                _screenState.value = ScreenState.Error
            }
        ) {
            _screenState.value = ScreenState.Loading

            val products =
                productRepository.getProducts(query.split(QUERY_SEPARATOR).joinToString())

            products
                .onSuccess { list ->

                    if (list.isEmpty()) {
                        _screenState.value = ScreenState.Error
                        return@onSuccess
                    }

                    _productList.update { list }
                    _screenState.value = ScreenState.Success
                }.onFailure {
                    _screenState.value = ScreenState.Error
                }

            hasSearched = true
        }
    }

    fun onProductClicked(product: Product) {
        executeSuspending {
            sendEvent(GeneralEvent.Navigation(ProductRoutes.ProductDetail(product.id)))
        }
    }

    companion object {
        const val QUERY_SEPARATOR = "%20"
    }
}
