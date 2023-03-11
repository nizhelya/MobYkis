package com.yuzhny.mykis.domain.appartment.request

import com.yuzhny.mykis.data.remote.GetSimpleResponse
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import com.yuzhny.mykis.domain.appartment.AppartmentRepository
import com.yuzhny.mykis.domain.interactor.UseCase
import javax.inject.Inject

class UpdateBti @Inject constructor(
    private val appartmentRepository: AppartmentRepository
) : UseCase<GetSimpleResponse, AppartmentEntity>() {

    override suspend fun run(params: AppartmentEntity) =
        appartmentRepository.updateBti(
            params.addressId,
            params.phone,
            params.email
        )
}
