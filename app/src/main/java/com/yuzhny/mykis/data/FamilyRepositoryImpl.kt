package com.yuzhny.mykis.data

import com.yuzhny.mykis.data.cache.family.FamilyCache
import com.yuzhny.mykis.data.cache.user.UserCache
import com.yuzhny.mykis.data.remote.family.FamilyRemote
import com.yuzhny.mykis.domain.family.FamilyEntity
import com.yuzhny.mykis.domain.family.FamilyRepository
import com.yuzhny.mykis.domain.family.request.FamilyBooleanInt
import com.yuzhny.mykis.domain.type.*

class FamilyRepositoryImpl (
    private val familyCache:FamilyCache,
    private val familyRemote: FamilyRemote,
    private val userCache: UserCache
) :FamilyRepository   {
    override fun getFamilyFromFlat(params: FamilyBooleanInt): Either<Failure, List<FamilyEntity>> {
        return  userCache.getCurrentUser()
            .flatMap { return@flatMap if (params.needFetch){
                    familyRemote.getFamilyFromFlat(params.addressId , it.userId , it.token)
                }else {
                    Either.Right(
                        familyCache.getFamilyFromFlat()
                    )
                }
            }
            .map { it.sortedBy { it.lastname } }
            .onNext { it.map { familyCache.addAppartmentByUser(listOf(it)) } }
    }

}