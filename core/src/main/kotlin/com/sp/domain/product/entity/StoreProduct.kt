package com.sp.domain.product.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "store_product")
class StoreProduct (product: Product, store: Store){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_product_no")
    var storeProductNo: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var product: Product = product

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var store: Store = store

    @Column(name = "reg_date")
    var regDate: Date? = null

    @Column(name = "mod_date")
    var modDate: Date? = null

    companion object{
        fun create(product:Product, store: Store) = StoreProduct(
            product = product,
            store = store
        )
    }
}