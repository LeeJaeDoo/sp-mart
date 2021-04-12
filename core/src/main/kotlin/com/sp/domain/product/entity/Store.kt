package com.sp.domain.product.entity

import com.sp.domain.product.entity.model.StoreRegisterModel
import javax.persistence.*

@Entity
@Table(name = "store")
data class Store (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_no")
    var no: Long? = null,

    @Column(name = "name")
    val name: String,

    @Column(name= "address")
    val address: String
){

    @OneToMany(mappedBy = "store")
    var storeProduct: List<StoreProduct>? = null

    companion object {
        fun create(param: StoreRegisterModel) = Store(
            name = param.name,
            address = param.address
        )
    }
}
