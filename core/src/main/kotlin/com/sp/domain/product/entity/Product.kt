package com.sp.domain.product.entity

import com.sp.domain.product.entity.model.ProductRegisterModel
import javax.persistence.*

@Entity
@Table( name = "product" )
class Product (name: String, price: Int){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_no")
    var no: Long = 0

    @Column(name="parent_no")
    var parentNo: Long? = null

    @Column(name = "name")
    var name: String = name

    @Column(name = "price")
    var price: Int = price

    @OneToMany(mappedBy = "product")
    var storeProduct: List<StoreProduct>? = null

    companion object{
        fun create(param: ProductRegisterModel) = Product(
            name = param.name,
            price = param.price
        )
    }

}