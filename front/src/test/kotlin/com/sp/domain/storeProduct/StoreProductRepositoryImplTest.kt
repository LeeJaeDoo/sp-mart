package com.sp.domain.storeProduct

import com.ninjasquad.springmockk.MockkBean
import com.querydsl.jpa.impl.JPAQueryFactory
import com.sp.application.StoreProductService
import com.sp.domain.extensions.toJson
import com.sp.domain.product.entity.QStoreProduct
import com.sp.infrastructure.queryDslConfig
import io.mockk.impl.annotations.MockK
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
class StoreProductRepositoryImplTest{
    private lateinit var spRepositoryImpl: StoreProductRepositoryImpl

    @Autowired
    @Resource(name = "jpaQueryFactory")
    lateinit var query : JPAQueryFactory

    @BeforeEach
    fun setUp(){
        spRepositoryImpl = StoreProductRepositoryImpl(query)
    }
    @Test
    fun `상품명으로 상점 리스트 조회`(){
        //when
        val storeList = spRepositoryImpl.getStoreList("koko", 1L)

        //then
        Assertions.assertEquals(storeList.get(0).store.name, "GS25")
        Assertions.assertEquals(storeList.get(0).store.address, "고척1동")
    }
}