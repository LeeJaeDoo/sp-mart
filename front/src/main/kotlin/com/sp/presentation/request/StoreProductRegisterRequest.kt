package com.sp.presentation.request

import com.sp.application.model.StoreProductRegisterApplicationModel

data class StoreProductRegisterRequest(
    val productName: String,
    val storeName: String,
    val address: String,
    val price: Int,
    val parentNo: Long,
    val count: Int,
    val memberNo: Long
) {
    fun valueOf() = StoreProductRegisterApplicationModel(
        productName = productName,
        storeName = storeName,
        address = address,
        price = price,
        count = count,
        parentNo = parentNo,
        memberNo = memberNo

    )
}