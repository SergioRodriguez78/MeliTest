package com.mercadolibre.melitest.product.detail.ui.molecules

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.mercadolibre.melitest.R


@Composable
fun MainDetailInformation(
    title: String,
    seller: String,
    price: String
) {
    Text(
        text = title,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.titleMedium
    )

    Text(
        text = stringResource(
            R.string.product_list_screen_seller_name,
            seller
        ),
        style = MaterialTheme.typography.titleSmall
    )

    Text(
        modifier = Modifier.padding(vertical = 10.dp),
        text = price,
        style = MaterialTheme.typography.titleLarge
    )
}
