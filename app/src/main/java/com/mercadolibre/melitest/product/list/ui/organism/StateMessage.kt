package com.mercadolibre.melitest.product.list.ui.organism

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mercadolibre.melitest.R
import com.mercadolibre.melitest.core.ScreenState


@Composable
fun StateMessage(
    modifier: Modifier,
    screenState: ScreenState
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        when (screenState) {
            is ScreenState.Loading -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(stringResource(R.string.product_list_screen_loading_text))
                    CircularProgressIndicator()
                }
            }

            is ScreenState.Error -> {
                Text(stringResource(R.string.product_list_screen_error_text))
            }

            ScreenState.Success -> {

            }
        }
    }
}
