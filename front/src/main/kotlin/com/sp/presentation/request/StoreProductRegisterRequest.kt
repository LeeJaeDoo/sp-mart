package com.sp.presentation.request

import com.sp.domain.product.entity.model.ProductRegisterModel
import com.sp.domain.product.entity.model.StoreProductRegisterModel
import com.sp.domain.product.entity.model.StoreRegisterModel

data class StoreProductRegisterRequest(
    val productName: String,
    val storeName: String,
    val address: String,
    val price: Int,
    val parentNo: Long,
    val count: Int
) {
    fun valueOf() = StoreProductRegisterModel(
        product = ProductRegisterModel(productName, price, parentNo),
        store = StoreRegisterModel(storeName, address),
        count = count
    )
}