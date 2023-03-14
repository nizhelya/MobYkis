package com.yuzhny.mykis.data.remote.service

import com.yuzhny.mykis.data.remote.core.BaseResponse
import com.yuzhny.mykis.domain.family.FamilyEntity
import com.yuzhny.mykis.domain.service.ServiceEntity

class GetServiceResponse (
    success:Int,
    message: String,
    val services: List<ServiceEntity>
) : BaseResponse(success, message)