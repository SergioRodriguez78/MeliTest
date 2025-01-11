package com.mercadolibre.melitest.product.detail.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.SubcomposeAsyncImage
import com.mercadolibre.melitest.core.ScreenState
import com.mercadolibre.melitest.product.detail.ui.molecules.MainDetailInformation
import com.mercadolibre.melitest.product.detail.ui.molecules.OtherDetailInformation
import com.mercadolibre.melitest.product.detail.viewmodel.ProductDetailViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductDetailScreen(
    productId: String,
    viewModel: ProductDetailViewModel = koinViewModel()
) {

    val productSelected by viewModel.selectedProduct.collectAsStateWithLifecycle()
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.onProductDetail(productId)
    }

    Column(
        modifier =
        Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        if (screenState == ScreenState.Loading) {
            CircularProgressIndicator()
            return@Column
        }


        SubcomposeAsyncImage(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(),
            model = productSelected?.image,
            contentScale = ContentScale.Fit,
            contentDescription = productSelected?.title,
            onError = {
                Log.e("Product detail", "Error loading image $it")
            }
        )

        MainDetailInformation(
            title = productSelected?.title.orEmpty(),
            seller = productSelected?.seller.orEmpty(),
            price = productSelected?.formattedPrice.toString()
        )

        OtherDetailInformation(
            availableQuantity = productSelected?.availableQuantity.toString(),
            place = productSelected?.place.orEmpty(),
            city = productSelected?.city.orEmpty()
        )

    }
}



