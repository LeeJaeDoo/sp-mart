package com.sp.application

import com.sp.domain.product.entity.StoreProduct
import com.sp.domain.storeProduct.StoreProductRepositoryImpl
import org.springframework.stereotype.Service

@Service
class StoreProductService(private val spRepository: StoreProductRepositoryImpl) {
    suspend fun getStoreList(name: String): List<StoreProduct> {
        return spRepository.getStoreList(name, 2L)
    }
}