package com.sp.presentation.request

import com.sp.application.model.productRegisterApplicationModel


data class ProductRegisterRequest(
    val name: String,
    val price: Int,
    val parentNo: Long
) {
    fun valueOf() = productRegisterApplicationModel(
        name = name,
        price = price,
        parentNo = parentNo
    )
}