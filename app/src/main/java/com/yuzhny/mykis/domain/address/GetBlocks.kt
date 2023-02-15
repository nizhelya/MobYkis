package com.yuzhny.mykis.domain.address

import com.yuzhny.mykis.domain.address.AddressRepository
import com.yuzhny.mykis.domain.interactor.UseCase
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure
import javax.inject.Inject

class GetBlocks @Inject constructor(
   private val addressRepository: AddressRepository
) : UseCase<List<AddressEntity>, Boolean>() {
    override suspend fun run(params: Boolean): Either<Failure, List<AddressEntity>> = addressRepository.getBlocks()
}