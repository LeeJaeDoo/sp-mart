package com.sp.presentation.handler

import com.sp.application.ProductService
import com.sp.domain.extensions.*
import com.sp.presentation.request.*
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.*
import kotlinx.coroutines.*
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.*
import org.springframework.http.*
import org.springframework.mock.http.server.reactive.*
import org.springframework.mock.web.server.*
import org.springframework.web.reactive.function.server.*

@ExtendWith(MockKExtension::class)
internal class ProductHandlerTest {
    @MockK
    private lateinit var productService: ProductService

    private lateinit var productHandler: ProductHandler

    @BeforeEach
    internal fun setUp(){
        productHandler = ProductHandler(productService)
    }

    @AfterEach
    internal fun after(){
        unmockkAll()
    }

    @Test
    fun `상품등록`() {
        val requestBody = ProductRegisterRequest(
            name = "꼬북칩",
            price = 1500
        )
        val request = MockServerHttpRequest
            .post("/backend/product")
            .contentType(MediaType.APPLICATION_JSON)
            .body(requestBody.toJson())

        val exchange = MockServerWebExchange
            .from(request)
            .let{ ServerRequest.create(it, HandlerStrategies.withDefaults().messageReaders()) }

        coEvery { productService.registerProduct(any()) } returns 1L

        //when
        val response = runBlocking { productHandler.register(exchange) }

        //then
        assertEquals(HttpStatus.CREATED, response.statusCode())

        coVerify { productService.registerProduct(any()) }
    }
}