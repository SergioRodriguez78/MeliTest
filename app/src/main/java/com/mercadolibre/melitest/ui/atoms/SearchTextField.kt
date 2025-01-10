package com.mercadolibre.melitest.ui.atoms

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.mercadolibre.melitest.R

@Composable
fun SearchTextField(
    modifier: Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit
) {
    TextField(
        modifier = modifier,
        value = query,
        onValueChange = onQueryChange,
        label = { Text(stringResource(R.string.search_screen_search_label)) },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),
        singleLine = true,
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch()
            }
        ),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = stringResource(R.string.search_screen_search_cd),
            )
        }
    )
}
