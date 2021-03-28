package com.sp.presentation.router

import com.sp.presentation.handler.ProductHandler
import org.springframework.context.annotation.*
import org.springframework.http.*
import org.springframework.web.reactive.function.server.*

@Configuration
class ProductRouter(private val productHandler: ProductHandler) {

    @Bean
    fun productRoutes(): RouterFunction<ServerResponse> {
        return coRouter {
            (accept(MediaType.APPLICATION_JSON) and "/product").nest {
                GET("", productHandler::findAll)
            }
        }
    }
}