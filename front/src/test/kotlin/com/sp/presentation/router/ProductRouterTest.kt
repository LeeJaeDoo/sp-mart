package com.sp.presentation.router

import com.ninjasquad.springmockk.*
import com.sp.application.*
import com.sp.presentation.handler.ProductHandler
import com.sp.presentation.handler.StoreProductHandler
import com.sp.presentation.request.ProductRegisterRequest
import com.sp.presentation.request.StoreProductRegisterRequest
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
import com.sp.presentation.FrontApiTestSupportFilterFunction
import com.epages.restdocs.apispec.*
import com.sp.presentation.*
import org.springframework.restdocs.payload.*

@WebFluxTest
@ExtendWith(RestDocumentationExtension::class)
@ContextConfiguration(classes = [ProductRouter::class, ProductHandler::class, StoreProductHandler::class])
internal class ProductRouterTest(private val context: ApplicationContext){
    private lateinit var webTestClient: WebTestClient

    @MockkBean
    private lateinit var productService: ProductService

    @MockkBean
    private lateinit var storeProductService: StoreProductService

    private val TAG = "PRODUCT"

    @BeforeEach
    fun setup(restDocumentation: RestDocumentationContextProvider) {
        webTestClient = WebTestClient.bindToApplicationContext(context)
            .configureClient()
            .baseUrl("http://localhost:8080")
            .filter(WebTestClientRestDocumentation.documentationConfiguration(restDocumentation))
            .filter(FrontApiTestSupportFilterFunction())
            .build()
    }
    @Test
    fun `상품 등록`() {
        val productNo = 1L
        val request = ProductRegisterRequest(
            name = "꼬북칩",
            price = 1500,
            parentNo = 1L
        )

        coEvery { productService.registerProduct(any()) } returns 1L

        webTestClient.post()
            .uri("/product")
            .header("Version", "1.0")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .bodyValue(request)
            .exchange()
            .expectStatus().isCreated
            .expectHeader().valueEquals(HttpHeaders.LOCATION, productNo.toString())
            .expectBody().consumeWith(
                WebTestClientRestDocumentation.document(
                    "product-register",
                    ResourceDocumentation.resource(
                        ResourceSnippetParameters.builder()
                            .tag(TAG)
                            .description("상품 등록")
                            .requestFields(
                                PayloadDocumentation.fieldWithPath("name")
                                    .description("상품명")
                                    .type(JsonFieldType.STRING),
                                PayloadDocumentation.fieldWithPath("price")
                                    .description("가격")
                                    .type(JsonFieldType.NUMBER),
                                PayloadDocumentation.fieldWithPath("parentNo")
                                    .description("부모 product 번호")
                                    .type(JsonFieldType.NUMBER)
                            ).build()
                    )
                )
            )
    }

    @Test
    fun `상품 no로 상점 리스트 조회`() {
        val productNo = 1L

        coEvery { storeProductService.getStoreList(any()) } returns listOf()

        webTestClient.get()
            .uri("/backend/product/" + productNo)
            .header("Version", "1.0")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectHeader().valueEquals(HttpHeaders.LOCATION, productNo.toString())
            .expectBody().consumeWith(
                WebTestClientRestDocumentation.document(
                    "product-no-find-all-store-list",
                    ResourceDocumentation.resource(
                        ResourceSnippetParameters.builder()
                            .tag(TAG)
                            .description("상품 번호로 상점 리스트 조회")
                            .build()
                    )
                )
            )

    }

    @Test
    fun `상품 상점 등록`(){
        val storeProductNo = 1L
        val request = StoreProductRegisterRequest(
            productName= "초코맛",
            storeName= "GS24",
            address= "고척1동",
            price= 1500,
            parentNo= 1L,
            count= 2,
            memberNo = 1L
        )

        coEvery { storeProductService.register(any()) } returns 1L

        webTestClient.post()
            .uri("/backend/product/storeProduct")
            .header("Version", "1.0")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .bodyValue(request)
            .exchange()
            .expectStatus().isCreated
            .expectHeader().valueEquals(HttpHeaders.LOCATION, storeProductNo.toString())
            .expectBody().consumeWith(
                WebTestClientRestDocumentation.document(
                    "store-product-register",
                    ResourceDocumentation.resource(
                        ResourceSnippetParameters.builder()
                            .tag(TAG)
                            .description("상품 상점 등록")
                            .requestFields(
                                PayloadDocumentation.fieldWithPath("productName")
                                    .description("상품명")
                                    .type(JsonFieldType.STRING),
                                PayloadDocumentation.fieldWithPath("address")
                                    .description("상점 주소")
                                    .type(JsonFieldType.STRING),
                                PayloadDocumentation.fieldWithPath("price")
                                    .description("가격")
                                    .type(JsonFieldType.NUMBER),
                                PayloadDocumentation.fieldWithPath("parentNo")
                                    .description("부모 product 번호")
                                    .type(JsonFieldType.NUMBER),
                                PayloadDocumentation.fieldWithPath("count")
                                    .description("개수")
                                    .type(JsonFieldType.NUMBER),
                                PayloadDocumentation.fieldWithPath("memberNo")
                                    .description("회원번호")
                                    .type(JsonFieldType.NUMBER)
                            ).build()
                    )
                )
            )
    }

    @Test
    fun `상품 전체 조회`(){
        val productNo = 1L
        coEvery { productService.getAllProductList() } returns listOf()

        webTestClient.get()
            .uri("/backend/product/list")
            .header("Version", "1.0")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectHeader().valueEquals(HttpHeaders.LOCATION, productNo.toString())
            .expectBody().consumeWith(
                WebTestClientRestDocumentation.document(
                    "product-all-list",
                    ResourceDocumentation.resource(
                        ResourceSnippetParameters.builder()
                            .tag(TAG)
                            .description("상품 전체 조회")
                            .build()
                    )
                )
            )
    }
}