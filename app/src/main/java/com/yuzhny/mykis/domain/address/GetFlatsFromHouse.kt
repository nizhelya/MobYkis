package com.yuzhny.mykis.domain.address

import com.yuzhny.mykis.domain.interactor.UseCase
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure
import javax.inject.Inject

class GetFlatsFromHouse  @Inject constructor(
    private val addressRepository: AddressRepository
) : UseCase<List<AddressEntity>,Int>() {
    override suspend fun run(params:Int): Either<Failure, List<AddressEntity>> = addressRepository.getFlatsFromHouse(params)
}