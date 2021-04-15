package com.sp.application

import com.ninjasquad.springmockk.MockkBean
import com.sp.domain.product.entity.StoreProduct
import com.sp.domain.storeProduct.StoreProductRepository
import com.sp.domain.storeProduct.StoreProductRepositorySupport
import com.sp.presentation.request.ProductRegisterRequest
import com.sp.presentation.request.StoreProductRegisterRequest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class StoreProductServiceTest {
    @MockK
    private lateinit var storeProductRepositorySupport: StoreProductRepositorySupport

    @MockK
    private lateinit var spRepository: StoreProductRepository

    private lateinit var storeProductService: StoreProductService

    @BeforeEach
    fun setUp(){
        storeProductService = StoreProductService(spRepository, storeProductRepositorySupport)
    }

    @Test
    fun `상품명으로 상점 리스트 조회`(){
        //given

        //when
        coEvery { storeProductRepositorySupport.getStoreList(any(), 1L) } returns listOf()
        runBlocking { storeProductService.getStoreList("koko") }

        //then
        coVerify { storeProductService.getStoreList("koko") }
    }

    @Test
    fun `상품 상점 등록`(){
        //given
        val storeProduct = StoreProductRegisterRequest(
            productName= "초코맛",
            storeName= "GS24",
            address= "고척1동",
            price= 1500,
            parentNo= 1L,
            count= 2
        )

        //when
        //val saved = storeProductRepository.save(StoreProduct.create(storeProduct.valueOf())).storeProductNo!!

        coEvery { spRepository.save(any()).storeProductNo } returns 1L
        runBlocking { storeProductService.register(storeProduct) }

        //then
        coVerify { storeProductService.register(any()) }

    }
}