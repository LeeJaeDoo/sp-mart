package com.sp.presentation.request

import com.sp.domain.product.entity.model.ProductRegisterModel

data class ProductRegisterRequest(
    val name: String,
    val price: Int,
    val parentNo: Long
) {
    fun valueOf() = ProductRegisterModel(
        name = name,
        price = price,
        parentNo = parentNo
    )
}