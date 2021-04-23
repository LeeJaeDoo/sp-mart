package com.sp.domain.storeProduct

import com.sp.domain.product.entity.StoreProduct
import com.sp.domain.product.entity.model.StoreProductRegisterModel
import org.springframework.stereotype.Service

@Service
class StoreProductDomainService(
    private val storeProductRepository: StoreProductRepository
) {
    suspend fun register(params: StoreProductRegisterModel): Long {
        return storeProductRepository.save(StoreProduct.create(params)).storeProductNo!!
    }

    suspend fun getStoreList(productNo: Long): List<StoreProduct> {
        return storeProductRepository.getStoreList(productNo)
    }
}