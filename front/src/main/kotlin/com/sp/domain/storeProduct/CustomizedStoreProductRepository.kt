package com.sp.domain.storeProduct

import com.sp.domain.product.entity.StoreProduct

interface CustomizedStoreProductRepository {
    fun getStoreList(productNo: Long): List<StoreProduct>
}