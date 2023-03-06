package com.yuzhny.mykis.domain.address

import com.yuzhny.mykis.data.remote.GetSimpleResponse
import com.yuzhny.mykis.data.remote.address.GetAddressResponse
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure

interface AddressRepository {
    fun getBlocks(): Either<Failure, List<AddressEntity>>
    fun getStreetsFromBlock(blockId: Int): Either<Failure, List<AddressEntity>>
    fun getHousesFromStreet(streetId: Int, blockId: Int): Either<Failure, List<AddressEntity>>
    fun getFlatsFromHouse(houseId: Int): Either<Failure, List<AddressEntity>>
    fun addFlatByUser(addressId: Int): Either<Failure, GetSimpleResponse>
    fun checkCode(kod: String, addressId: Int): Either<Failure, GetSimpleResponse>
}