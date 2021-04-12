package com.sp.domain

import com.sp.domain.product.entity.*
import org.springframework.data.jpa.repository.*

/**
 * @author Jaedoo Lee
 */
interface StoreProductRepository : JpaRepository<StoreProduct, Long>
