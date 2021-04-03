package com.sp.presentation.router

import com.sp.presentation.handler.ProductHandler
import org.springframework.context.annotation.*
import org.springframework.http.*
import org.springframework.web.reactive.function.server.*
import reactor.core.publisher.Mono

@Configuration
class ProductRouter(private val productHandler: ProductHandler) {

    @Bean
    fun routeProductGet(): RouterFunction<ServerResponse> {
        return coRouter {
            ("/backend/product" and headers { "1.0" in it.header("Version") }).nest {
                accept(MediaType.APPLICATION_JSON).nest {
                    GET("", productHandler::findAll)
                }
            }
        }
    }

    @Bean
    fun routeProductPost(): RouterFunction<ServerResponse> {
        return coRouter {
            ("/backend/product" and headers { "1.0" in it.header("Version") }).nest {
                accept(MediaType.APPLICATION_JSON).nest {
                    POST("", productHandler::register)
                }
            }
        }
    }
}