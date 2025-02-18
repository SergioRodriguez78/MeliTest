package com.mercadolibre.melitest.product.model

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

data class Product(
    val id: String,
    val title: String,
    val image: String,
    val seller: String,
    val price: Double,
    val place: String,
    val availableQuantity: Int,
    val city: String
) {
    val formattedPrice: String
        get() {
            val symbols = DecimalFormatSymbols(Locale.getDefault()).apply {
                groupingSeparator = '.'
                decimalSeparator = ','
            }
            val format = DecimalFormat("#,###,###,###.##", symbols).apply {
                maximumFractionDigits = 2
            }
            return "$ ${format.format(price)}"
        }
}
