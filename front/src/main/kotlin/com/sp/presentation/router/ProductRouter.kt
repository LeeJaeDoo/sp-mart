package com.sp.presentation.router

import com.sp.presentation.handler.ProductHandler
import org.springframework.context.annotation.*
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class ProductRouter {

    @Bean
    fun productRoutes(productHandler: ProductHandler) = coRouter {
        GET("/product", productHandler::findAll)
        GET("/product/{title}", productHandler::findByTitle)
    }
}