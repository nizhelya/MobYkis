package com.yuzhny.mykis.data.remote.appartment

import com.yuzhny.mykis.data.remote.GetSimpleResponse
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

    override fun getAppartmentsByUser(
        userId: Int,
        token: String
    ): Either<Failure, List<AppartmentEntity>> {
        return request.make(
            service.getAppartmentsByUser(
                createGetAppartmentsByUserMap(
                    userId,
                    token
                )
            )
        ) {
            it.appartments
        }
    }

    override fun deleteFlatByUser(
        addressId: Int,
        userId: Int,
        token: String
    ): Either<Failure, GetSimpleResponse> {
        return request.make(
            service.deleteFlatByUser(
                createDeleteFlatByUserMap(
                    addressId,
                    userId,
                    token
                )
            )
        ){
            it
        }
    }
    override fun updateBti(
        addressId: Int,
        phone: String,
        email: String,
        userId: Int,
        token: String
    ): Either<Failure, GetSimpleResponse> {
        return  request.make(
            service.updateBti(
                createUpdateBti(
                    addressId,
                    phone,
                    email,
                    userId,
                    token
                )
            )
        ){
            it
        }
    }

    private fun createGetAppartmentsByUserMap(userId: Int, token: String): Map<String, String> {
        val map = HashMap<String, String>()
        map.put(ApiService.PARAM_USER_ID, userId.toString())
        map.put(ApiService.PARAM_TOKEN, token)
        return map
    }
    private fun createDeleteFlatByUserMap(addressId:Int , userId: Int, token: String): Map<String, String> {
        val map = HashMap<String, String>()
        map.put(ApiService.PARAM_ADDRESS_ID , addressId.toString())
        map.put(ApiService.PARAM_USER_ID, userId.toString())
        map.put(ApiService.PARAM_TOKEN, token)
        return map
    }
    private fun createUpdateBti(addressId: Int,phone:String , email:String , userId: Int, token: String): Map<String, String> {
        val map = HashMap<String, String>()
        map.put(ApiService.ADDRESS_ID, addressId.toString())
        map.put(ApiService.PHONE, phone)
        map.put(ApiService.EMAIL, email)
        map.put(ApiService.PARAM_USER_ID, userId.toString())
        map.put(ApiService.PARAM_TOKEN, token)
        return map
    }
}