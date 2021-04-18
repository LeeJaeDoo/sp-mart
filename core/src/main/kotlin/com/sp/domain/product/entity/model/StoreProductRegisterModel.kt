package com.sp.domain.product.entity.model

import com.sp.domain.product.entity.Product
import com.sp.domain.product.entity.Store

data class StoreProductRegisterModel(
    val product: ProductRegisterModel,
    val store: StoreRegisterModel,
    val count: Int,
    val memberNo: Long
)
