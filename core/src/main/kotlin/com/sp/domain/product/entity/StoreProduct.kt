package com.sp.domain.product.entity

import com.sp.domain.product.entity.model.*
import java.time.*
import javax.persistence.*

@Entity
@Table(name = "store_product")
class StoreProduct (product: Product, store: Store){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_product_no")
    var storeProductNo: Long? = null

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "product_no")
    var product: Product = product

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "store_no")
    var store: Store = store

    @Column(name = "reg_date")
    var regDate: LocalDateTime? = LocalDateTime.now()

    @Column(name = "mod_date")
    var modDate: LocalDateTime? = null

    companion object{
        fun create(params: StoreProductRegisterModel) = StoreProduct(
            product = Product.create(params.product),
            store = Store.create(params.store)
        )
    }
}
