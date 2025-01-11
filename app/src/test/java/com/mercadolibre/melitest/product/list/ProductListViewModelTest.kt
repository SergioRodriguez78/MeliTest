package com.mercadolibre.melitest.product.list

import com.mercadolibre.melitest.BaseTest
import com.mercadolibre.melitest.core.ResultMELI
import com.mercadolibre.melitest.core.ScreenState
import com.mercadolibre.melitest.notification.NotificationManager
import com.mercadolibre.melitest.product.data.ProductRepository
import com.mercadolibre.melitest.product.list.viewmodel.ProductListViewModel
import com.mercadolibre.melitest.product.model.Product
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ProductListViewModelTest : BaseTest() {


    private val productRepository = mockk<ProductRepository>()

    private val notificationManager = mockk<NotificationManager>()


    private lateinit var viewModel: ProductListViewModel

    @Before
    override fun setUp() {

        super.setUp()

        viewModel = ProductListViewModel(
            productRepository = productRepository,
            notificationManager = notificationManager
        )
    }


    @Test
    fun `findProducts should update product list on success`() = runTest {
        // Given
        val mockProducts = listOf(TEST_PRODUCT1, TEST_PRODUCT2)
        val query = "query"

        val response: ResultMELI<List<Product>> = ResultMELI.success(mockProducts)

        coEvery { productRepository.getProducts(query) } answers {
            response
        }

        // When
        viewModel.findProducts(query)

        // Then
        assert(viewModel.screenState.value is ScreenState.Success)
        assertEquals(mockProducts, viewModel.productList.value)
    }

    @Test
    fun `findProducts should show error notification and set screen state as error on failure`() =
        runTest {
            // Given
            val query = "query"
            val errorMessage = "Error"

            val response: ResultMELI<List<Product>> = ResultMELI.error(Exception(errorMessage))

            coEvery { productRepository.getProducts(query) } answers {
                response
            }

            every {
                notificationManager.showError(errorMessage)
            } returns Unit

            // When
            viewModel.findProducts(query)

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

        private val TEST_PRODUCT2 = Product(
            id = "2",
            title = "Producto 2",
            image = "",
            seller = "Vendedor 2",
            price = 2000.0,
            place = "Ciudad B",
            availableQuantity = 20,
            city = "Ciudad B"
        )


    }


}