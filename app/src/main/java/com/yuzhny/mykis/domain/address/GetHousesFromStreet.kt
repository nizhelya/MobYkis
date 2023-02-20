package com.yuzhny.mykis.domain.address

import android.text.PrecomputedText.Params
import com.yuzhny.mykis.domain.interactor.UseCase
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure
import javax.inject.Inject


class GetHousesFromStreet @Inject constructor(
    private val addressRepository: AddressRepository
) : UseCase<List<AddressEntity>,AddressEntity>() {
    override suspend fun run(params:AddressEntity): Either<Failure, List<AddressEntity>> =
        addressRepository.getHousesFromStreet(params.streetId , params.blockId)
}