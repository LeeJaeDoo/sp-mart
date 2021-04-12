package com.sp.presentation.handler

import com.sp.application.ProductService
import com.sp.domain.ProductRepository
import com.sp.presentation.request.ProductRegisterRequest
import com.sp.presentation.response.*
import org.springframework.stereotype.*
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.*
import java.net.URI

@Component
class ProductHandler (
    private val productService: ProductService
) {
    suspend fun findAll(request: ServerRequest): ServerResponse =
        ok().json().bodyValueAndAwait(productService.findAllProducts()!!)

    suspend fun findById(request: ServerRequest): ServerResponse {
        val product = productService.findById(request.pathVariable("id").toLong())
        return ok().json().bodyValueAndAwait(ProductResponse(product!!.name, product.price))
    }


    suspend fun register(request: ServerRequest): ServerResponse {
        val params = request.awaitBody<ProductRegisterRequest>()
        val memberNo = productService.registerProduct(params)
        return created(URI.create(memberNo.toString())).buildAndAwait()
    }
}
