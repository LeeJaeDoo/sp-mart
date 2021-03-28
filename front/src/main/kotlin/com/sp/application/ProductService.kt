package com.sp.application

import com.sp.domain.ProductDomainService
import com.sp.domain.model.Product
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ProductService(
    private val productDomainService: ProductDomainService
) {
    fun findAllProducts():MutableList<Product> {
        return productDomainService.findAllProducts()
    }

    suspend fun findByTitle(title: String): Mono<Product> {
        return productDomainService.findByTitle(title)
    }
}