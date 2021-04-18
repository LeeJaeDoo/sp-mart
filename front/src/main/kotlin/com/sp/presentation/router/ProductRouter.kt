package com.sp.presentation.router

import com.sp.presentation.handler.ProductHandler
import com.sp.presentation.handler.StoreProductHandler
import org.springframework.context.annotation.*
import org.springframework.http.*
import org.springframework.web.reactive.function.server.*
import reactor.core.publisher.Mono

@Configuration
class ProductRouter(
    private val productHandler: ProductHandler,
    private val storeProductHandler: StoreProductHandler
) {

    @Bean
    fun routeProductGet(): RouterFunction<ServerResponse> {
        return coRouter {
            ("/backend/product" and headers { "1.0" in it.header("Version") }).nest {
                accept(MediaType.APPLICATION_JSON).nest {
                    POST("", productHandler::register)
                    GET("/allList", productHandler::getAllProductList)
                }
            }
        }
    }

    @Bean
    fun routeStoreProductGet(): RouterFunction<ServerResponse> {
        return coRouter {
            ("/backend/storeProduct" and headers { "1.0" in it.header("Version") }).nest {
                accept(MediaType.APPLICATION_JSON).nest {
                    POST("", storeProductHandler::register)
                    GET("/{id}", storeProductHandler::getStoreList)
                }
            }
        }
    }
}