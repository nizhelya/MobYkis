package com.yuzhny.mykis.data.remote.family

import com.yuzhny.mykis.data.remote.core.Request
import com.yuzhny.mykis.data.remote.service.ApiService
import com.yuzhny.mykis.domain.family.FamilyEntity
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FamilyRemoteImpl @Inject constructor(
    private val request: Request,
    private val service: ApiService
) : FamilyRemote {

    override fun getFamilyFromFlat(
        addressId: Int,
        userId: Int,
        token: String
    ): Either<Failure, List<FamilyEntity>> {
        return request.make(
            service.getFamilyFromFlat(
                createGetFamilyFromFlatMap(
                    addressId,
                    userId,
                    token
                )
            )
        ) {
            it.family
        }
    }

    private fun createGetFamilyFromFlatMap(
        addressId: Int,
        userId: Int,
        token: String
    ): Map<String, String> {
        val map = HashMap<String, String>()
        map.put(ApiService.ADDRESS_ID, addressId.toString())
        map.put(ApiService.PARAM_USER_ID, userId.toString())
        map.put(ApiService.PARAM_TOKEN, token)
        return map
    }
}