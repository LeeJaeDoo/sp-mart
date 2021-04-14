package com.sp.application

import com.sp.domain.product.ProductDomainService
import com.sp.domain.product.entity.Product
import com.sp.presentation.request.ProductRegisterRequest
import org.springframework.stereotype.Service
import java.util.*


@Service
class ProductService(
    private val productDomainService: ProductDomainService
) {
    suspend fun registerProduct(params: ProductRegisterRequest): Long {
        return productDomainService.register(params)
    }

}