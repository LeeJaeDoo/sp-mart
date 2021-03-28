package com.sp.domain.model

import javax.persistence.*

@Entity
@Table(name="product")
data class Product(
    @Id
    @Column(name = "id")
    var id: Int,

    @Column(name = "title")
    var title: String,

    @Column(name = "location")
    var location: String
)
