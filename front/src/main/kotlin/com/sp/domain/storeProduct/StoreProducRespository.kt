package com.sp.domain.storeProduct

import com.sp.domain.product.entity.StoreProduct
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor

interface StoreProducRespository: JpaRepository<StoreProduct, Long>, QuerydslPredicateExecutor<StoreProduct>