package com.sp.application

import com.sp.domain.product.ProductDomainService
import com.sp.domain.storeProduct.StoreProductRepositoryImpl
import com.sp.presentation.request.ProductRegisterRequest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class StoreProductServiceTest {
    @MockK
    private lateinit var storeProductRepositoryImpl: StoreProductRepositoryImpl

    private lateinit var storeProductService: StoreProductService

    @BeforeEach
    fun setUp(){
        storeProductService = StoreProductService(storeProductRepositoryImpl)
    }

    @Test
    fun `상품명으로 상점 리스트 조회`(){
        //given

        //when
        coEvery { storeProductRepositoryImpl.getStoreList(any(), 1L) } returns listOf()
        runBlocking { storeProductService.getStoreList("koko") }

        //then
        coVerify { storeProductService.getStoreList("koko") }
    }
}