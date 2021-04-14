package com.sp.presentation.router

import com.ninjasquad.springmockk.*
import com.sp.application.*
import com.sp.domain.product.entity.Product
import com.sp.domain.product.entity.StoreProduct
import com.sp.presentation.handler.ProductHandler
import com.sp.presentation.handler.StoreProductHandler
import com.sp.presentation.request.ProductRegisterRequest
import io.mockk.coEvery
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.context.ApplicationContext
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.reactive.server.WebTestClient

@WebFluxTest
@ExtendWith(RestDocumentationExtension::class)
@ContextConfiguration(classes = [ProductRouter::class, ProductHandler::class, StoreProductHandler::class])
internal class ProductRouterTest(private val context: ApplicationContext){
    private lateinit var webTestClient: WebTestClient

    @MockkBean
    private lateinit var productService: ProductService

    @MockkBean
    private lateinit var storeProductService: StoreProductService

    @BeforeEach
    fun setup(restDocumentation: RestDocumentationContextProvider) {
        webTestClient = WebTestClient.bindToApplicationContext(context)
            .configureClient()
            .baseUrl("http://localhost:8080")
            .filter(WebTestClientRestDocumentation.documentationConfiguration(restDocumentation))
            .build()
    }
    @Test
    fun `부모 상품 등록`() {
        val request = ProductRegisterRequest(
            name = "꼬북칩",
            price = 1500
        )

        coEvery { productService.registerProduct(any()) } returns 1L

        webTestClient.post()
            .uri("/backend/product")
            .header("Version", "1.0")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .bodyValue(request)
            .exchange()
            .expectStatus().isCreated
    }

    @Test
    fun `상품명으로 상점 리스트 조회`() {
        val productName = "koko"

        coEvery { storeProductService.getStoreList(any()) } returns listOf()

        webTestClient.get()
            .uri("/backend/product/"+productName)
            .header("Version", "1.0")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
    }
}