package com.sp.domain

import com.sp.domain.product.entity.Product
import com.sp.presentation.request.ProductRegisterRequest
import org.springframework.data.repository.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.*

@Service
class ProductDomainService(private val productRepository: ProductRepository) {

    suspend fun findAllProducts(): List<Product> {
        return productRepository.findAll()
    }

    suspend fun findById(id: Long): Product? {
        return productRepository.findByIdOrNull(id)
    }

//    suspend fun register(params: ProductRegisterRequest): Long {
//        return productRepository.save(Product.create(params.valueOf())).no
//    }
}


