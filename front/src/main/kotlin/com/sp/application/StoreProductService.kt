package com.sp.application

import com.sp.application.model.StoreProductRegisterApplicationModel
import com.sp.domain.product.entity.StoreProduct
import com.sp.domain.storeProduct.StoreProductDomainService
import org.springframework.stereotype.Service
import org.springframework.transaction.support.TransactionTemplate

@Service
class StoreProductService(
    private val storeProductDomainService: StoreProductDomainService,
    private val transactionTemplate: TransactionTemplate,

    ) {

    suspend fun register(params: StoreProductRegisterApplicationModel): Long {
        return storeProductDomainService.register(params.valueOf())
    }

    suspend fun getStoreList(productNo: Long): List<StoreProduct> {
        val allProductStoreList = storeProductDomainService.getStoreList(productNo)
        return transactionTemplate.execute {
            allProductStoreList
        }!!
    }
}