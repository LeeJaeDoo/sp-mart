package com.sp.application

import com.sp.domain.ProductDomainService
import com.sp.domain.product.entity.Product
import com.sp.presentation.request.ProductRegisterRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*


@Service
@Transactional(readOnly = true)
class ProductService(
    private val productDomainService: ProductDomainService
) {
    @Transactional
    suspend fun findAllProducts():List<Product> {
        return productDomainService.findAllProducts()
    }

    suspend fun findById(id: Long): Optional<Product> {
        return productDomainService.findById(id)
    }

    suspend fun registerProduct(params: ProductRegisterRequest): Long {
        return productDomainService.register(params)
    }

}