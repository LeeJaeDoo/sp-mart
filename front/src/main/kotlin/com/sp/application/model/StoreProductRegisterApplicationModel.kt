package com.sp.application.model

import com.sp.domain.product.entity.model.ProductRegisterModel
import com.sp.domain.product.entity.model.StoreProductRegisterModel
import com.sp.domain.product.entity.model.StoreRegisterModel

data class StoreProductRegisterApplicationModel (
    val productName: String,
    val storeName: String,
    val address: String,
    val price: Int,
    val parentNo: Long,
    val count: Int,
    val memberNo: Long
    ) {
    fun valueOf() = StoreProductRegisterModel(
        product = ProductRegisterModel(productName, price, parentNo),
        store = StoreRegisterModel(storeName, address),
        count = count,
        memberNo = memberNo
    )
}