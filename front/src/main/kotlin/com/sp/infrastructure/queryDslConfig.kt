package com.sp.infrastructure

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext


@Configuration
class queryDslConfig {
    @PersistenceContext
    private val entityManager: EntityManager? = null

    @Bean
    open fun jpaQueryFactory(): JPAQueryFactory? {
        return JPAQueryFactory(entityManager)
    }
}