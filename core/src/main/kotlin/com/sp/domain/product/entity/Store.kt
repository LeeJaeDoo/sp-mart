package com.sp.domain.product.entity

import com.sp.domain.product.entity.model.StoreRegisterModel
import javax.persistence.*

@Entity
@Table(name = "store")
class Store (name: String, address: String){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_no")
    var no: Long? = null

    @Column(name = "name")
    var name: String = name

    @Column(name= "address")
    var address: String = address

    @OneToMany(mappedBy = "store")
    var storeProduct: List<StoreProduct>? = null

    companion object {
        fun create(param: StoreRegisterModel) = Store(
            name = param.name,
            address = param.address
        )
    }
}