package com.mercadolibre.melitest.product.search.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mercadolibre.melitest.R
import com.mercadolibre.melitest.core.GeneralEvent
import com.mercadolibre.melitest.product.search.viewmodel.SearchScreenViewModel
import com.mercadolibre.melitest.ui.atoms.SearchTextField
import com.mercadolibre.melitest.ui.theme.MercadoLibreBlue
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel


@Composable
fun SearchScreen(
    navigator: NavController,
    viewModel: SearchScreenViewModel = koinViewModel()
) {
    val query by viewModel.query.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.event.collectLatest {
            when (it) {
                is GeneralEvent.Navigation -> navigator.navigate(it.route)
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier.padding(bottom = 40.dp),
                painter = painterResource(id = R.drawable.mercado_libre_logo),
                contentDescription = stringResource(R.string.search_screen_search_cd),
            )

            SearchTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .padding(horizontal = 32.dp),
                query = query,
                onQueryChange = viewModel::setQuery,
                onSearch = viewModel::onSearch,
                enabledSearch = query.isNotBlank(),
            )

            Button(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth(),
                enabled = query.isNotBlank(),
                onClick = viewModel::onSearch,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MercadoLibreBlue,
                )
            ) {
                Text(stringResource(R.string.search_screen_search_button))
            }
        }
    }
}
