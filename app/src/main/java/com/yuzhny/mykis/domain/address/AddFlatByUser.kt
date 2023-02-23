package com.yuzhny.mykis.domain.address

import com.yuzhny.mykis.data.remote.address.GetAddressResponse
import com.yuzhny.mykis.data.remote.core.BaseResponse
import com.yuzhny.mykis.domain.interactor.UseCase
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure
import javax.inject.Inject

class AddFlatByUser @Inject constructor(
    private val addressRepository: AddressRepository
) : UseCase<List<AddressEntity>, Int>() {
    override suspend fun run(params:Int): Either<Failure, List<Any>> = addressRepository.addFlatByUser(params)
}