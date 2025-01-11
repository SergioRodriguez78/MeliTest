package com.mercadolibre.melitest.product.detail

import com.mercadolibre.melitest.BaseTest
import com.mercadolibre.melitest.core.ResultMELI
import com.mercadolibre.melitest.core.ScreenState
import com.mercadolibre.melitest.notification.NotificationManager
import com.mercadolibre.melitest.product.data.ProductRepository
import com.mercadolibre.melitest.product.detail.viewmodel.ProductDetailViewModel
import com.mercadolibre.melitest.product.model.Product
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class ProductDetailViewModelTest : BaseTest() {

    private val productRepository = mockk<ProductRepository>()

    private val notificationManager = mockk<NotificationManager>()

    private lateinit var viewModel: ProductDetailViewModel

    @Before
    override fun setUp() {

        super.setUp()

        viewModel = ProductDetailViewModel(
            productRepository = productRepository,
            notificationManager = notificationManager
        )
    }

    @Test
    fun `onProductDetail should update selected product on success`() {
        // Given
        val productId = "productId"
        val product = TEST_PRODUCT1

        coEvery { productRepository.getProductDetail(productId) } answers {
            ResultMELI.success(product)
        }

        // When
        viewModel.onProductDetail(productId)

        // Then
        assert(viewModel.screenState.value is ScreenState.Success)
        assertEquals(product, viewModel.selectedProduct.value)
    }

    @Test
    fun `onProductDetail should update screen state to error on failure`() {
        // Given
        val productId = "productId"
        val errorMessage = "Error message"
        val error = Exception(errorMessage)

        coEvery { productRepository.getProductDetail(productId) } answers {
            ResultMELI.error(error)
        }

        every {
            notificationManager.showError(errorMessage)
        } returns Unit

        // When
        viewModel.onProductDetail(productId)

        // Then
        assert(viewModel.screenState.value is ScreenState.Error)
    }

    companion object {
        private val TEST_PRODUCT1 = Product(
            id = "1",
            title = "Producto 1",
            image = "",
            seller = "Vendedor 1",
            price = 1000.0,
            place = "Ciudad A",
            availableQuantity = 10,
            city = "Ciudad A"
        )
    }
}
