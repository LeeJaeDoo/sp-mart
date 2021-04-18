package com.sp.domain.storeProduct

import com.sp.domain.product.entity.StoreProduct
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.stereotype.Repository

@Repository
interface StoreProductRepository: JpaRepository<StoreProduct, Long>, CustomizedStoreProductRepository