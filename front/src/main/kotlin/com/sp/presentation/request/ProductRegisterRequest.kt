package com.sp.presentation.request

import com.sp.domain.product.entity.model.*

data class ProductRegisterRequest(
    val productName: String,
    val storeName: String,
    val address: String,
    val price: Int
) {
    fun valueOf() = StoreProductRegisterModel(
        product = ProductRegisterModel(productName, price),
        store = StoreRegisterModel(storeName, address)
    )
}
