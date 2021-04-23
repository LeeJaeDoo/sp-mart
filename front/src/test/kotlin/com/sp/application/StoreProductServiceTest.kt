package com.sp.application

import com.sp.application.model.StoreProductRegisterApplicationModel
import com.sp.domain.storeProduct.StoreProductDomainService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.transaction.support.*

@ExtendWith(MockKExtension::class)
internal class StoreProductServiceTest {
    @MockK
    private lateinit var storeProductDomainService: StoreProductDomainService

    private lateinit var storeProductService: StoreProductService

    @MockK
    private lateinit var transactionTemplate: TransactionTemplate

    @BeforeEach
    fun setUp(){
        storeProductService = StoreProductService(storeProductDomainService, transactionTemplate)

        every { transactionTemplate.execute(any<TransactionCallback<*>>()) } answers {
            (firstArg() as TransactionCallback<*>).doInTransaction(mockk())
        }
    }

    @Test
    fun `상품명으로 상점 리스트 조회`(){
        //given

        //when
        coEvery { storeProductService.getStoreList(any()) } returns listOf()
        runBlocking { storeProductService.getStoreList(1L) }

        //then
        coVerify { storeProductService.getStoreList(1L) }
    }

    @Test
    fun `상품 상점 등록`(){
        //given
        val storeProduct = StoreProductRegisterApplicationModel(
            productName= "초코맛",
            storeName= "GS24",
            address= "고척1동",
            price= 1500,
            parentNo= 1L,
            count= 2,
            memberNo = 1L
        )

        //when
        coEvery{ storeProductDomainService.register(any()) } returns 1L
        runBlocking { storeProductService.register(storeProduct) }

        //then
        coVerify { storeProductService.register(any()) }

    }
}