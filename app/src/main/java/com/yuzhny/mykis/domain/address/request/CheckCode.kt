package com.yuzhny.mykis.domain.address.request

import com.yuzhny.mykis.data.remote.address.GetAddressResponse
import com.yuzhny.mykis.domain.address.AddressRepository
import com.yuzhny.mykis.domain.interactor.UseCase
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure
import javax.inject.Inject

class CheckCode @Inject constructor(
    private val addressRepository: AddressRepository
) : UseCase<GetAddressResponse, FlatCode>() {
    override suspend fun run(params:FlatCode): Either<Failure, GetAddressResponse> = addressRepository.checkCode(params.kod , params.addressId)
}

data class FlatCode(
    val kod :String ,
    val addressId:Int,
)