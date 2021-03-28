package com.sp.domain

import com.sp.domain.model.Product
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ProductDomainService(private val productRepository: ProductRepository) {

    fun findAllProducts(): MutableList<Product> {
        return productRepository.findAll()
    }

    suspend fun findByTitle(title: String): Mono<Product> {
        return productRepository.findByTitle(title)
    }
}


