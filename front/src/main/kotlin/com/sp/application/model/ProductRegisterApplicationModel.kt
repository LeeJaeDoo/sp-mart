package com.sp.application.model

import com.sp.domain.product.entity.model.ProductRegisterModel

data class ProductRegisterApplicationModel (
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
