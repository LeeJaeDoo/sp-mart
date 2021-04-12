package com.sp.application

import com.sp.domain.*
import com.sp.domain.product.entity.*
import com.sp.presentation.request.ProductRegisterRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.support.*


@Service
class ProductService(
    private val productDomainService: ProductDomainService,
    private val transactionTemplate: TransactionTemplate,
    private val storeProductRepository: StoreProductRepository
) {

    @Transactional(readOnly = true)
    suspend fun findAllProducts():List<Product>? {
        return productDomainService.findAllProducts()
    }

    suspend fun findById(id: Long): Product? {
        return productDomainService.findById(id)
    }

    suspend fun registerProduct(params: ProductRegisterRequest): Long {
        return transactionTemplate.execute {
            storeProductRepository.save(StoreProduct.create(params.valueOf())).storeProductNo
        }!!
    }

}
