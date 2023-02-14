package com.yuzhny.mykis.data

import com.yuzhny.mykis.data.cache.appartment.AppartmentCache
import com.yuzhny.mykis.data.cache.user.UserCache
import com.yuzhny.mykis.data.remote.appartment.AppartmentRemote
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import com.yuzhny.mykis.domain.appartment.AppartmentRepository
import com.yuzhny.mykis.domain.type.*


class AppartmentRepositoryImpl (private val appartmentRemote: AppartmentRemote,
                                private val appartmentCache: AppartmentCache,
                                private val userCache: UserCache
):AppartmentRepository{
    override fun getAppartmentsByUser(needFetch: Boolean): Either<Failure, List<AppartmentEntity>> {
        return userCache.getCurrentUser()
            .flatMap {
                return@flatMap if (needFetch) {
                    appartmentCache.deleteAllAppartments(it.userId)
                    appartmentRemote.getAppartmentsByUser(it.userId,it.token)
                } else {
                    Either.Right(appartmentCache.getAppartmentsByUser())
                }
            }
            .map { it.sortedBy { it.address } }
            .onNext { it.map { appartmentCache.addAppartmentByUser(listOf(it)) } }
    }
}