package com.yuzhny.mykis.domain.address

import com.yuzhny.mykis.data.remote.address.GetAddressResponse
import com.yuzhny.mykis.data.remote.core.BaseResponse
import com.yuzhny.mykis.domain.BaseResponseData
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure
import com.yuzhny.mykis.domain.type.None

interface AddressRepository {
    fun getBlocks(): Either<Failure, List<AddressEntity>>
    fun getStreetsFromBlock(blockId:Int): Either<Failure, List<AddressEntity>>
    fun getHousesFromStreet(streetId:Int,blockId:Int): Either<Failure, List<AddressEntity>>
    fun getFlatsFromHouse(houseId:Int): Either<Failure, List<AddressEntity>>
    fun addFlatByUser(addressId:Int):Either<Failure, GetAddressResponse>
}