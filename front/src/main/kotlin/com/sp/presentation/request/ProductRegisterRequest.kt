package com.sp.presentation.request

import com.sp.application.model.ProductRegisterApplicationModel


data class ProductRegisterRequest(
    val name: String,
    val price: Int,
    val parentNo: Long
) {
    fun valueOf() = ProductRegisterApplicationModel(
        name = name,
        price = price,
        parentNo = parentNo
    )
}