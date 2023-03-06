package com.yuzhny.mykis.data.remote.address

import com.yuzhny.mykis.data.remote.GetSimpleResponse
import com.yuzhny.mykis.domain.address.AddressEntity
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure

interface AddressRemote {
    fun getBlocks(userId:Int, token: String): Either<Failure, List<AddressEntity>>
    fun getStreetsFromBlock(blockId:Int, userId:Int, token: String): Either<Failure, List<AddressEntity>>
    fun getHousesFromStreet(streetId:Int , blockId:Int, userId:Int, token: String): Either<Failure, List<AddressEntity>>
    fun getFlatsFromHouse(houseId:Int, userId:Int, token: String): Either<Failure, List<AddressEntity>>
    fun addFlatsByUser(addressId:Int , userId: Int , token: String): Either<Failure ,GetSimpleResponse>
    fun checkCode(kod:String , addressId:Int , userId: Int ,  token: String): Either<Failure , GetSimpleResponse>
}