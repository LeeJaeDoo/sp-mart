package com.sp.presentation.handler

import com.sp.repository.ProductRepository
import org.springframework.stereotype.*
import org.springframework.web.reactive.function.server.*
@Component
class ProductHandler (
    private var productRepository: ProductRepository
) {
    //@FlowPreview
    suspend fun findAll(request: ServerRequest): ServerResponse =
        ServerResponse.ok().json().bodyValueAndAwait(productRepository.findAll())

    suspend fun findByTitle(request: ServerRequest): ServerResponse {
        val title = request.pathVariable("title")
        return ServerResponse.ok().json().bodyValueAndAwait(productRepository.findByTitle(title))
    }
}