package com.sp.domain.product

import com.sp.domain.product.entity.Product
import com.sp.presentation.request.ProductRegisterRequest
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductDomainService(private val productRepository: ProductRepository) {

    suspend fun findAllProducts(): List<Product> {
        return productRepository.findAll()
    }

    suspend fun findById(id: Long): Optional<Product> {
        return productRepository.findById(id)
    }

    suspend fun register(params: ProductRegisterRequest): Long {
        return productRepository.save(Product.create(params.valueOf())).no
    }
}


