package com.mercadolibre.melitest.product.detail.ui.molecules

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mercadolibre.melitest.R

@Composable
fun OtherDetailInformation(
    availableQuantity: String,
    place: String,
    city: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                color = Color.LightGray,
                width = 1.dp,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Column(
            Modifier.padding(16.dp)
        ) {
            Text(
                text = stringResource(
                    R.string.product_detail_screen_quantity,
                    availableQuantity
                ),
                style = MaterialTheme.typography.bodyLarge
            )

            Text(place, style = MaterialTheme.typography.bodyLarge)
            Text(city, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
