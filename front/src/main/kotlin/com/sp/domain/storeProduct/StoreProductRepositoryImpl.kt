package com.sp.domain.storeProduct

import com.querydsl.jpa.impl.JPAQueryFactory
import com.sp.domain.product.entity.QStoreProduct
import com.sp.domain.product.entity.StoreProduct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import javax.annotation.Resource


class StoreProductRepositoryImpl(
    @Autowired
    @Resource(name = "jpaQueryFactory")
    var query : JPAQueryFactory
    ): QuerydslRepositorySupport(StoreProduct::class.java), CustomizedStoreProductRepository {

    val storeProduct = QStoreProduct.storeProduct

    override fun getStoreList(productNo: Long): List<StoreProduct> {
        return query
            .select(storeProduct)
            .from(storeProduct)
            .where(storeProduct.product.no.eq(productNo))
            .fetch()
    }
}