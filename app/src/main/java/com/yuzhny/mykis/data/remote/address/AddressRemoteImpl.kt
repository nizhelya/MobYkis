package com.yuzhny.mykis.data.remote.address

import com.yuzhny.mykis.data.remote.core.Request
import com.yuzhny.mykis.data.remote.service.ApiService
import com.yuzhny.mykis.data.remote.service.ApiService.Companion.KOD
import com.yuzhny.mykis.domain.address.AddressEntity
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

    override fun getFlatsFromHouse(
        houseId: Int,
        userId: Int,
        token: String
    ): Either<Failure, List<AddressEntity>> {
        return request.make(
            service.getFlatsFromHouse(
                createGetFlatsMap(
                    houseId,
                    userId,
                    token
                )
            )
        ) {
            it.address
        }
    }

    override fun addFlatsByUser(addressId: Int, userId: Int, token: String) :Either<Failure , GetAddressResponse>{
        return request.make(
            service.addFlatsByUser(
                createAddFlatsMap(
                    addressId,
                    userId,
                    token
                )
            )
        )
            {
                it
            }

    }

    override fun checkCode(
        kod: String,
        addressId: Int,
        userId: Int,
        token: String
    ): Either<Failure, GetAddressResponse> {
        return  request.make(
            service.checkCode(
                createCheckCodeMap(
                    kod,
                    addressId,
                    userId,
                    token
                )
            )
        ){
           it
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
        map.put(ApiService.STREET_ID, streetId.toString())
        map.put(ApiService.BLOCK_ID, blockId.toString())
        map.put(ApiService.PARAM_USER_ID, userId.toString())
        map.put(ApiService.PARAM_TOKEN, token)
        return map
    }private fun createGetFlatsMap(houseId: Int,userId: Int, token: String): Map<String, String> {
        val map = HashMap<String, String>()
        map.put(ApiService.HOUSE_ID, houseId.toString())
        map.put(ApiService.PARAM_USER_ID, userId.toString())
        map.put(ApiService.PARAM_TOKEN, token)
        return map
    }
    private fun createAddFlatsMap(addressId: Int,userId: Int, token: String): Map<String, String> {
        val map = HashMap<String, String>()
        map.put(ApiService.ADDRESS_ID, addressId.toString())
        map.put(ApiService.PARAM_USER_ID, userId.toString())
        map.put(ApiService.PARAM_TOKEN, token)
        return map
    }
    private fun createCheckCodeMap(kod:String ,addressId: Int,userId: Int, token: String): Map<String, String> {
        val map = HashMap<String, String>()
        map.put(ApiService.KOD , kod)
        map.put(ApiService.ADDRESS_ID, addressId.toString())
        map.put(ApiService.PARAM_USER_ID, userId.toString())
        map.put(ApiService.PARAM_TOKEN, token)
        return map
    }
}