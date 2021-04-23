package com.sp.application

import com.sp.application.model.ProductRegisterApplicationModel
import com.sp.domain.product.ProductDomainService
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.*
import kotlinx.coroutines.*
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.*
import org.springframework.transaction.support.*

/**
 * @author sojeong park
 */
@ExtendWith(MockKExtension::class)
internal class ProductServiceTest {
    @MockK
    private lateinit var productDomainService: ProductDomainService

    @MockK
    private lateinit var transactionTemplate: TransactionTemplate

    private lateinit var productService: ProductService

    @BeforeEach
    fun setUp(){
        productService = ProductService(productDomainService, transactionTemplate)

        every { transactionTemplate.execute(any<TransactionCallback<*>>()) } answers {
            (firstArg() as TransactionCallback<*>).doInTransaction(mockk())
        }
    }

    @Test
    fun `상품등록`(){
        //given
        val request = ProductRegisterApplicationModel(
            name = "koko",
            price = 1500,
            parentNo = 1L
        )

        //when
        coEvery { productDomainService.register(any()) } returns 1L
        runBlocking { productService.registerProduct(request) }

        //then
        coVerify { productService.registerProduct(any()) }

    }

    @Test
    fun `상품전체조회`(){
        //when
        coEvery { productDomainService.findAllProducts() } returns listOf()
        runBlocking { productService.getAllProductList() }

        //then
        coVerify { productService.getAllProductList() }
    }
}