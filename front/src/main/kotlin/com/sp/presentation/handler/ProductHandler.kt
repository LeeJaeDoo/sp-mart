package com.sp.presentation.handler

import com.sp.application.ProductService
import com.sp.domain.ProductRepository
import com.sp.presentation.request.ProductRegisterRequest
import org.springframework.stereotype.*
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.*
import java.net.URI

@Component
class ProductHandler (
    private val productService: ProductService
) {
    suspend fun findAll(request: ServerRequest): ServerResponse =
        ServerResponse.ok().json().bodyValueAndAwait(productService.findAllProducts())

    suspend fun findById(request: ServerRequest): ServerResponse =
            ServerResponse.ok().json().bodyValueAndAwait(productService.findById(request.pathVariable("id").toLong()))

    suspend fun register(request: ServerRequest): ServerResponse {
        val params = request.awaitBody<ProductRegisterRequest>()
        val memberNo = productService.registerProduct(params)
        return created(URI.create(memberNo.toString())).buildAndAwait()
    }
}