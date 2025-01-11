package com.mercadolibre.melitest.product.list.ui.organism

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.mercadolibre.melitest.product.list.ui.molecules.ProductItemDetail


@Composable
fun ProductItem(
    modifier: Modifier,
    title: String,
    image: String,
    seller: String,
    price: String,
    place: String,
    onItemClicked: () -> Unit
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onItemClicked()
            }
    ) {
        Box(
            modifier = Modifier
                .size(width = 150.dp, height = 100.dp)
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = image,
                contentScale = ContentScale.Crop,
                contentDescription = title,
                onError = {
                    Log.e("ProductItem", "Error loading image $it")
                }
            )
        }

        ProductItemDetail(
            title = title,
            seller = seller,
            price = price,
            place = place
        )
    }
}

