package com.sp.domain.storeProduct

import com.querydsl.jpa.impl.JPAQueryFactory
import com.sp.application.model.StoreProductRegisterApplicationModel
import org.junit.jupiter.api.Assertions.*
import com.sp.domain.product.entity.StoreProduct
import com.sp.infrastructure.queryDslConfig
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import
import org.springframework.stereotype.Repository
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor
import javax.annotation.Resource

@DataJpaTest
@ActiveProfiles("test")
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(useDefaultFilters = false, includeFilters = [ComponentScan.Filter(Repository::class)])
@Import(queryDslConfig::class)
class StoreProductRepositoryImplTest(
    val storeProductRepository: StoreProductRepository
) {
    private lateinit var spRepositoryImpl: StoreProductRepositoryImpl

    @Autowired
    @Resource(name = "jpaQueryFactory")
    lateinit var query : JPAQueryFactory

    @BeforeEach
    fun setUp(){
        spRepositoryImpl = StoreProductRepositoryImpl(query)
    }
    @Test
    fun `상품 no로 상점 리스트 조회`(){
        //when
        val storeList = spRepositoryImpl.getStoreList(1L)

        //then
        Assertions.assertEquals(storeList.get(0).store.name, "GS25")
        Assertions.assertEquals(storeList.get(0).store.address, "고척1동")
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
        val saved = storeProductRepository.save(StoreProduct.create(storeProduct.valueOf())).storeProductNo!!

        //then
        val result = storeProductRepository.findById(saved).get()
        assertEquals(storeProduct.address, result.store.address)
        assertEquals(storeProduct.productName, result.product.name)
        assertEquals(storeProduct.price, result.product.price)
    }
}