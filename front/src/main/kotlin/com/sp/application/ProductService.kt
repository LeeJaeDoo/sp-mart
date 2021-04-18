package com.sp.application

import com.sp.application.model.productRegisterApplicationModel
import com.sp.domain.product.ProductDomainService
import com.sp.domain.product.entity.Product
import org.springframework.stereotype.Service
import org.springframework.transaction.support.TransactionTemplate

@Service
class ProductService(
    private val productDomainService: ProductDomainService,
    private val transactionTemplate: TransactionTemplate,
) {
    suspend fun registerProduct(params: productRegisterApplicationModel): Long {
        return productDomainService.register(params.valueOf())
    }

    suspend fun getAllProductList(): List<Product> {
        val allProductList = productDomainService.findAllProducts()
        return transactionTemplate.execute {
            allProductList
        }!!
    }

}