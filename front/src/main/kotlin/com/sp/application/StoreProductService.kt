package com.sp.application

import com.sp.domain.product.entity.StoreProduct
import com.sp.domain.storeProduct.StoreProductRepository
import com.sp.domain.storeProduct.StoreProductRepositorySupport
import com.sp.presentation.request.ProductRegisterRequest
import com.sp.presentation.request.StoreProductRegisterRequest
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.server.ServerRequest

@Service
class StoreProductService(
    private val spRepository: StoreProductRepository,
    private val spRepositorySupport: StoreProductRepositorySupport
    ) {

    suspend fun register(params: StoreProductRegisterRequest): Long {
        return spRepository.save(StoreProduct.create(params.valueOf())).storeProductNo!!
    }

    suspend fun getStoreList(name: String): List<StoreProduct> {
        return spRepositorySupport.getStoreList(name, 2L)
    }
}