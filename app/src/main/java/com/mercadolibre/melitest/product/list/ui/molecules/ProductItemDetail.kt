package com.mercadolibre.melitest.product.list.ui.molecules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.mercadolibre.melitest.R


@Composable
fun ProductItemDetail(
    title: String,
    seller: String,
    price: String,
    place: String
) {
    Column(
        modifier = Modifier.padding(start = 6.dp)
    ) {
        Text(
            text = title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleSmall
        )

        Text(
            text = stringResource(R.string.product_list_screen_seller_name, seller),
            style = MaterialTheme.typography.bodySmall
        )

        Text(
            modifier = Modifier.padding(vertical = 4.dp),
            text = price,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
        )


        Text(place, style = MaterialTheme.typography.bodySmall)
    }
}
