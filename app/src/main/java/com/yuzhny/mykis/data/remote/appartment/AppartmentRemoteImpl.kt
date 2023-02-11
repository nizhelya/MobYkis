package com.yuzhny.mykis.data.remote.appartment

import com.yuzhny.mykis.data.remote.core.Request
import com.yuzhny.mykis.data.remote.service.ApiService
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppartmentRemoteImpl @Inject constructor(
    private val request: Request,
    private val service: ApiService
) : AppartmentRemote {

    override fun getAppartments(
        addressId: Int,
        token: String
    ): Either<Failure, List<AppartmentEntity>> {
        return request.make(
            service.getAppartments(
                createGetAppartmentsMap(
                    addressId,
                    token
                )
            )
        ) { it.appartments }
    }

    private fun createGetAppartmentsMap(addressId: Int, token: String): Map<String, String> {
        val map = HashMap<String, String>()
        map.put(ApiService.PARAM_ADDRESS_ID, addressId.toString())
        map.put(ApiService.PARAM_TOKEN, token)
        return map
    }
}