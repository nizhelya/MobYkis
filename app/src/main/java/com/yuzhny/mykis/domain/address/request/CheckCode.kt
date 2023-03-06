package com.yuzhny.mykis.domain.address.request

import com.yuzhny.mykis.data.remote.GetSimpleResponse
import com.yuzhny.mykis.data.remote.address.GetAddressResponse
import com.yuzhny.mykis.domain.address.AddressRepository
import com.yuzhny.mykis.domain.interactor.UseCase
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure
import javax.inject.Inject

class CheckCode @Inject constructor(
    private val addressRepository: AddressRepository
) : UseCase<GetSimpleResponse, FlatCode>() {
    override suspend fun run(params:FlatCode): Either<Failure, GetSimpleResponse> = addressRepository.checkCode(params.kod , params.addressId)
}

data class FlatCode(
    val kod :String ,
    val addressId:Int,
)