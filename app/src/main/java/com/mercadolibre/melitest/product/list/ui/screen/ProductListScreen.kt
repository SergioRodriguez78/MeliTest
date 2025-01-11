package com.mercadolibre.melitest.product.list.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.mercadolibre.melitest.R
import com.mercadolibre.melitest.core.GeneralEvent
import com.mercadolibre.melitest.core.ScreenState
import com.mercadolibre.melitest.navigation.routes.product.ProductRoutes
import com.mercadolibre.melitest.product.list.ui.organism.ProductList
import com.mercadolibre.melitest.product.list.ui.organism.StateMessage
import com.mercadolibre.melitest.product.list.viewmodel.ProductListViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductListScreen(
    query: ProductRoutes.ProductList,
    navigator: NavHostController,
    viewModel: ProductListViewModel = koinViewModel()
) {
    val products by viewModel.productList.collectAsStateWithLifecycle()
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.findProducts(query.query)

        viewModel.event.collectLatest {
            when (it) {
                is GeneralEvent.Navigation -> {
                    navigator.navigate(it.route)
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            maxLines = 2,
            text = stringResource(R.string.product_list_screen_title, query.query),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Left
        )

        when (screenState) {
            is ScreenState.Loading, ScreenState.Error -> {
                StateMessage(
                    modifier = Modifier.fillMaxSize(),
                    screenState = screenState
                )
            }

            ScreenState.Success -> {
                ProductList(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    products = products,
                    onProductClicked = { product ->
                        viewModel.onProductClicked(product)
                    }
                )
            }
        }
    }
}
