package com.yuzhny.mykis.data

import com.yuzhny.mykis.data.cache.appartment.AppartmentCache
import com.yuzhny.mykis.data.cache.family.FamilyCache
import com.yuzhny.mykis.data.cache.user.UserCache
import com.yuzhny.mykis.data.remote.GetSimpleResponse
import com.yuzhny.mykis.data.remote.appartment.AppartmentRemote
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import com.yuzhny.mykis.domain.appartment.AppartmentRepository
import com.yuzhny.mykis.domain.type.*
import io.reactivex.rxjava3.internal.util.HalfSerializer.onNext


class AppartmentRepositoryImpl(
    private val appartmentRemote: AppartmentRemote,
    private val appartmentCache: AppartmentCache,
    private val familyCache: FamilyCache,
    private val userCache: UserCache
) : AppartmentRepository {
    val addressIdList = mutableListOf<Int>()

    override fun getAppartmentsByUser(needFetch: Boolean): Either<Failure, List<AppartmentEntity>> {
        return userCache.getCurrentUser()
            .flatMap {
                return@flatMap if (needFetch) {
                    appartmentRemote.getAppartmentsByUser(it.userId, it.token)

                } else {
                    Either.Right(
                        appartmentCache.getAppartmentsByUser()
                    )
                }
            }
            .map { it.sortedBy { it.address } }
            .onNext {
                appartmentCache.deleteAllAppartments()
            }
            .onNext {
                for( i in it){
                    addressIdList.add(i.addressId)
                }
                familyCache.deleteFamilyFromFlat(addressIdList)
                addressIdList.clear()
            }
            .onNext {
                it.map {
                    appartmentCache.addAppartmentByUser(listOf(it))
                }
            }
    }

    override fun deleteFlatByUser(
        addressId: Int
    ): Either<Failure, GetSimpleResponse> {
        return userCache.getCurrentUser()
            .flatMap {
                return@flatMap appartmentRemote.deleteFlatByUser(
                    addressId,
                    it.userId,
                    it.token
                )
            }
    }
    override fun updateBti(
        addressId: Int,
        phone: String,
        email: String
    ): Either<Failure, GetSimpleResponse> {
        return userCache.getCurrentUser()
            .flatMap { return@flatMap appartmentRemote.updateBti(addressId , phone , email , it.userId , it.token) }
    }
}