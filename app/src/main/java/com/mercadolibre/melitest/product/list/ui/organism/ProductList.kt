package com.mercadolibre.melitest.product.list.ui.organism

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mercadolibre.melitest.product.model.Product

@Composable
fun ProductList(
    modifier: Modifier,
    products: List<Product>,
    onProductClicked: (Product) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(products.size) { productIndex ->
            val product = products[productIndex]

            ProductItem(
                modifier = Modifier.padding(6.dp),
                title = product.title,
                image = product.image,
                seller = product.seller,
                price = product.formattedPrice,
                place = product.place
            ) {
                onProductClicked(product)
            }

            HorizontalDivider(modifier = Modifier.padding(top = 4.dp))

        }

    }
}
