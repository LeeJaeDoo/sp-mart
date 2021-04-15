package com.sp.presentation.handler

import com.sp.application.StoreProductService
import com.sp.presentation.request.ProductRegisterRequest
import com.sp.presentation.request.StoreProductRegisterRequest
import org.springframework.stereotype.*
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.*
import java.net.URI

@Component
class StoreProductHandler(
    private val storeProductService: StoreProductService
) {
    suspend fun register(request: ServerRequest): ServerResponse {
        val params = request.awaitBody<StoreProductRegisterRequest>()
        val storeProductNo = storeProductService.register(params)
        return created(URI.create(storeProductNo.toString())).buildAndAwait()
    }

    suspend fun getStoreList(request: ServerRequest): ServerResponse =
        ServerResponse.ok().json().bodyValueAndAwait(storeProductService.getStoreList(request.pathVariable("name")))
}