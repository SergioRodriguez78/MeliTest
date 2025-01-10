package com.mercadolibre.melitest.product.search.viewmodel

import com.mercadolibre.melitest.core.BaseViewModel
import com.mercadolibre.melitest.core.GeneralEvent
import com.mercadolibre.melitest.navigation.routes.product.ProductRoutes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SearchScreenViewModel : BaseViewModel() {

    private var _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    fun setQuery(query: String) {
        _query.update { query }
    }

    fun onSearch() {
        executeSuspending {
            sendEvent(
                GeneralEvent.Navigation(
                    ProductRoutes.ProductList(
                        query = query.value
                    )
                )
            )
        }
    }
}
