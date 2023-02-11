package com.yuzhny.mykis.domain.appartment

import com.yuzhny.mykis.data.cache.appartment.AppartmentCache
import com.yuzhny.mykis.domain.interactor.UseCase
import javax.inject.Inject

class GetAppartments  @Inject constructor(
    private val appartmentRepository: AppartmentRepository
) : UseCase<List<AppartmentEntity>, Boolean>() {

    override suspend fun run(needFetch: Boolean) = appartmentRepository.getAppartments(needFetch)
}