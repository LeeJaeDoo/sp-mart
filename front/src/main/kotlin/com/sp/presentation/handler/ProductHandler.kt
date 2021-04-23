package com.sp.presentation.handler

import com.sp.application.ProductService
import com.sp.presentation.request.ProductRegisterRequest
import org.springframework.stereotype.*
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.*
import java.net.URI

@Component
class ProductHandler (
    private val productService: ProductService
) {
    suspend fun register(request: ServerRequest): ServerResponse {
        val params = request.awaitBody<ProductRegisterRequest>()
        val productNo = productService.registerProduct(params.valueOf())
        return created(URI.create(productNo.toString())).buildAndAwait()
    }

    suspend fun getAllProductList(request: ServerRequest): ServerResponse =
        ServerResponse.ok().json().bodyValueAndAwait(productService.getAllProductList())

}