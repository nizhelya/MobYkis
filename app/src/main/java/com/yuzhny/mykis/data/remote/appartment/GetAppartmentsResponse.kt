package com.yuzhny.mykis.data.remote.appartment

import com.yuzhny.mykis.data.remote.core.BaseResponse
import com.yuzhny.mykis.domain.appartment.AppartmentEntity

class GetAppartmentsResponse (
    success:Int,
    message: String,
    val appartments: List<AppartmentEntity>
) : BaseResponse(success, message)