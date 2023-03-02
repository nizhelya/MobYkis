package com.yuzhny.mykis.domain.address.request

import com.yuzhny.mykis.data.remote.address.GetAddressResponse
import com.yuzhny.mykis.domain.address.AddressRepository
import com.yuzhny.mykis.domain.interactor.UseCase
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure
import javax.inject.Inject

class AddFlatByUser @Inject constructor(
    private val addressRepository: AddressRepository
) : UseCase<GetAddressResponse, Int>() {
    override suspend fun run(params:Int): Either<Failure, GetAddressResponse> = addressRepository.addFlatByUser(params)
}