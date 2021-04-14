package com.sp.presentation.handler

import com.sp.application.StoreProductService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.json

@Component
class StoreProductHandler(
    private val storeProductService: StoreProductService
) {
    suspend fun getStoreList(request: ServerRequest): ServerResponse =
        ServerResponse.ok().json().bodyValueAndAwait(storeProductService.getStoreList(request.pathVariable("name")))
}