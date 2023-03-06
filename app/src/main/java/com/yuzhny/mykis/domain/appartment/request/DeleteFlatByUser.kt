package com.yuzhny.mykis.domain.appartment.request

import com.yuzhny.mykis.data.remote.GetSimpleResponse
import com.yuzhny.mykis.domain.appartment.AppartmentRepository
import com.yuzhny.mykis.domain.interactor.UseCase
import javax.inject.Inject

class DeleteFlatByUser @Inject constructor(
    private val appartmentRepository: AppartmentRepository
) : UseCase<GetSimpleResponse, Int>() {

    override suspend fun run(params:Int) = appartmentRepository.deleteFlatByUser(params)
}