package com.yuzhny.mykis.domain.service.request

import com.yuzhny.mykis.data.ServiceRepositoryImpl
import com.yuzhny.mykis.domain.interactor.UseCase
import com.yuzhny.mykis.domain.service.ServiceEntity
import javax.inject.Inject

class getTotalDebtService @Inject constructor(
    private val serviceRepository: ServiceRepositoryImpl
) : UseCase<ServiceEntity? , Int>() {

    override suspend fun run(params:Int) = serviceRepository.getTotalFlatService(params)
}