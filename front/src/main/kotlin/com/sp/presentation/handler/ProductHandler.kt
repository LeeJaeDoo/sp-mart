package com.sp.presentation.handler

import com.sp.application.ProductService
import com.sp.domain.ProductDomainService
import com.sp.domain.ProductRepository
import org.springframework.stereotype.*
import org.springframework.web.reactive.function.server.*
@Component
class ProductHandler (
    private var productService: ProductService,
) {
    //@FlowPreview
    suspend fun findAll(request: ServerRequest): ServerResponse =
        ServerResponse.ok().json().bodyValueAndAwait(productService.findAllProducts())

    suspend fun findByTitle(request: ServerRequest): ServerResponse {
        val title = request.pathVariable("title")
        return ServerResponse.ok().json().bodyValueAndAwait(productService.findByTitle(title))
    }
}