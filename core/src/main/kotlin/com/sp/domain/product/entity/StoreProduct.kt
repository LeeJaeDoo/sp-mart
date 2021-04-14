package com.sp.domain.product.entity

import com.sp.domain.product.entity.model.StoreProductRegisterModel
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "store_product")
class StoreProduct (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_product_no")
    var storeProductNo: Long? = null,

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "product_no")
    var product: Product,

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name="store_no")
    var store: Store,

    @Column(name = "reg_date")
    var regDate: LocalDateTime? = LocalDateTime.now(),

    @Column(name = "mod_date")
    var modDate: LocalDateTime? = LocalDateTime.now(),

    @Column(name = "count")
    var count: Int? = 0

    ){
    companion object{
        fun create(params: StoreProductRegisterModel) = StoreProduct(
            product = Product.create(params.product),
            store = Store.create(params.store),
            count = params.count
        )
    }
}