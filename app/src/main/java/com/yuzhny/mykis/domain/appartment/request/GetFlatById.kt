package com.yuzhny.mykis.domain.appartment.request

import com.yuzhny.mykis.data.remote.appartment.GetAppartmentsResponse
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import com.yuzhny.mykis.domain.appartment.AppartmentRepository
import com.yuzhny.mykis.domain.interactor.UseCase
import javax.inject.Inject

class GetFlatById  @Inject constructor(
    private val appartmentRepository: AppartmentRepository
) : UseCase<AppartmentEntity, Int>() {

    override suspend fun run(params:Int) = appartmentRepository.getFlatById(params)
}