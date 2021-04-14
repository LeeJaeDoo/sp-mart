package com.sp.domain.storeProduct

import com.querydsl.core.types.Predicate
import com.querydsl.jpa.impl.JPAQueryFactory
import com.sp.domain.product.entity.QProduct.product
import com.sp.domain.product.entity.QStoreProduct
import com.sp.domain.product.entity.StoreProduct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import javax.annotation.Resource
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class StoreProductRepositoryImpl(
    @Autowired
    @Resource(name = "jpaQueryFactory")
    var query : JPAQueryFactory
    ): QuerydslRepositorySupport(StoreProduct::class.java) {

    val storeProduct = QStoreProduct.storeProduct

    fun getStoreList(name: String, pageSize: Long) :List<StoreProduct> {
        return query
            .select(storeProduct)
            .from(storeProduct)
            .where(storeProduct.product.name.containsIgnoreCase(name))
            .limit(pageSize)
            .fetch()
    }
}