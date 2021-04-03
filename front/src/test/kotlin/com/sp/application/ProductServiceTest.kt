package com.sp.application

import com.sp.domain.ProductDomainService
import com.sp.presentation.request.*
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.*
import kotlinx.coroutines.*
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.*
import org.junit.jupiter.api.Assertions.*

/**
 * @author sojeong park
 */
@ExtendWith(MockKExtension::class)
internal class ProductServiceTest {
    @MockK
    private lateinit var productDomainService: ProductDomainService

    private lateinit var productService: ProductService

    @BeforeEach
    fun setUp(){
        productService = ProductService(productDomainService)
    }

    @Test
    fun `상품등록`(){
        //given
        val request = ProductRegisterRequest(
            name = "koko",
            price = 1500
        )

        //when
        coEvery { productDomainService.register(any()) } returns 1L
        runBlocking { productService.registerProduct(request) }

        //then
        coVerify { productService.registerProduct(any()) }

    }
}