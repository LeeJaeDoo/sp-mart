package com.sp.domain.product

import com.sp.domain.product.ProductRepository
import com.sp.domain.product.entity.Product
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.*
import org.springframework.boot.test.autoconfigure.jdbc.*
import org.springframework.boot.test.autoconfigure.orm.jpa.*
import org.springframework.context.annotation.*
import org.springframework.stereotype.*
import org.springframework.test.context.*

@DataJpaTest
@ActiveProfiles("test")
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(useDefaultFilters = false, includeFilters = [ComponentScan.Filter(Repository::class)])
class ProductRepositoryTest(
    val productRepository: ProductRepository
) {
    @Test
    fun `상품등록`(){
        //given
        val product = Product(
            name = "꼬북",
            price= 1500
        )

        //when
        val saved = productRepository.save(product)

        //then
        val result = productRepository.findById(saved.no).get()
        assertEquals(product.no, result.no)
        assertEquals(product.name, result.name)
        assertEquals(product.price, result.price)
    }
}