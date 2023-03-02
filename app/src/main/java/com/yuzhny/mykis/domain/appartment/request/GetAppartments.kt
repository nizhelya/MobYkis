package com.yuzhny.mykis.domain.appartment.request

import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import com.yuzhny.mykis.domain.appartment.AppartmentRepository
import com.yuzhny.mykis.domain.interactor.UseCase
import javax.inject.Inject

class GetAppartments  @Inject constructor(
    private val appartmentRepository: AppartmentRepository
) : UseCase<List<AppartmentEntity>, Boolean>() {

    override suspend fun run(needFetch: Boolean) = appartmentRepository.getAppartmentsByUser(needFetch)
}