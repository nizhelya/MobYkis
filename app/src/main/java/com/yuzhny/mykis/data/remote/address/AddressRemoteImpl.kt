package com.yuzhny.mykis.data.remote.address

import com.yuzhny.mykis.data.remote.appartment.AppartmentRemote
import com.yuzhny.mykis.data.remote.core.Request
import com.yuzhny.mykis.data.remote.service.ApiService
import com.yuzhny.mykis.domain.address.AddressEntity
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddressRemoteImpl @Inject constructor(
    private val request: Request,
    private val service: ApiService
) : AddressRemote {

    override fun getBlocks(
        userId: Int,
        token: String
    ): Either<Failure, List<AddressEntity>> {
        return request.make(
            service.getBlocks(
                createGetBlocksMap(
                    userId,
                    token
                )
            )
        ) {
            it.address
        }
    }

    override fun getStreetsFromBlock(
        blockId: Int,
        userId: Int,
        token: String
    ): Either<Failure, List<AddressEntity>> {
        return request.make(
            service.getStreetsFromBlock(
                createGetStreetsMap(
                    blockId,
                    userId,
                    token
                )
            )
        ) {
            it.address
        }
    }

    override fun getHousesFromStreet(
        streetId: Int,
        blockId: Int,
        userId: Int,
        token: String
    ): Either<Failure, List<AddressEntity>> {
        return request.make(
            service.getHousesFromStreet(
                createGetHousesMap(
                    streetId,
                    blockId,
                    userId,
                    token
                )
            )
        ) {
            it.address
        }
    }


    private fun createGetBlocksMap(userId: Int, token: String): Map<String, String> {
        val map = HashMap<String, String>()
        map.put(ApiService.PARAM_USER_ID, userId.toString())
        map.put(ApiService.PARAM_TOKEN, token)
        return map
    }
    private fun createGetStreetsMap(blockId:Int,userId: Int, token: String): Map<String, String> {
        val map = HashMap<String, String>()
        map.put(ApiService.BLOCK_ID, blockId.toString())
        map.put(ApiService.PARAM_USER_ID, userId.toString())
        map.put(ApiService.PARAM_TOKEN, token)
        return map
    }
    private fun createGetHousesMap(streetId: Int , blockId: Int,userId: Int, token: String): Map<String, String> {
        val map = HashMap<String, String>()
        map.put(ApiService.BLOCK_ID, blockId.toString())
        map.put(ApiService.STREET_ID, streetId.toString())
        map.put(ApiService.PARAM_USER_ID, userId.toString())
        map.put(ApiService.PARAM_TOKEN, token)
        return map
    }
}