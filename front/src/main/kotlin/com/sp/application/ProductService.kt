package com.sp.application

import com.sp.domain.product.ProductDomainService
import com.sp.presentation.request.ProductRegisterRequest
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productDomainService: ProductDomainService
) {
    suspend fun registerProduct(params: ProductRegisterRequest): Long {
        return productDomainService.register(params)
    }

}