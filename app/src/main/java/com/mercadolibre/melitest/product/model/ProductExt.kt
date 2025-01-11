package com.mercadolibre.melitest.product.model

import com.mercadolibre.melitest.product.data.remote.model.SearchResponseDTO

fun SearchResponseDTO.toProductList(): List<Product> {
    return results.map { dto ->
        Product(
            id = dto.id,
            title = dto.title,
            image = dto.thumbnail,
            seller = dto.seller?.name.orEmpty(),
            price = dto.price,
            place = dto.address?.stateName.orEmpty()
        )
    }
}
