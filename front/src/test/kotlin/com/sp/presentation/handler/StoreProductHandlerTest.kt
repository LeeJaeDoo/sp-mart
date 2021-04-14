package com.sp.presentation.handler

import com.sp.application.StoreProductService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.unmockkAll
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.http.HttpStatus
import org.springframework.mock.http.server.reactive.MockServerHttpRequest
import org.springframework.mock.web.server.MockServerWebExchange
import org.springframework.web.reactive.function.server.HandlerStrategies
import org.springframework.web.reactive.function.server.ServerRequest

@ExtendWith(MockKExtension::class)
internal class StoreProductHandlerTest{
    @MockK
    private lateinit var storeProductService: StoreProductService

    private lateinit var storeProductHandler: StoreProductHandler

    @BeforeEach
    internal fun setUp(){
        storeProductHandler = StoreProductHandler(storeProductService)
    }

    @AfterEach
    internal fun after(){
        unmockkAll()
    }

    @Test
    fun `상품명으로 상점 리스트 조회`(){
        val request = MockServerHttpRequest
            .get("/backend/product/koko")

        val exchange = MockServerWebExchange
            .from(request)
            .let{ ServerRequest.create(it, HandlerStrategies.withDefaults().messageReaders())}

        coEvery { storeProductService.getStoreList(any()) } returns listOf()

        //when
        val response = runBlocking { storeProductHandler.getStoreList(exchange) }

        //then
        assertEquals(HttpStatus.CREATED, response.statusCode())

        coVerify { storeProductService.getStoreList(any()) }
    }
}