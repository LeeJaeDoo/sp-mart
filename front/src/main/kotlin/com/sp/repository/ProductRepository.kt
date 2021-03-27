package com.sp.repository

import com.sp.presentation.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import reactor.core.publisher.*

interface ProductRepository : JpaRepository<Product, Long>{
    override fun findAll(): MutableList<Product>
    suspend fun findByTitle(title: String): Mono<Product>
}