package com.yuzhny.mykis.data.remote.water

import com.squareup.moshi.Json
import com.yuzhny.mykis.data.remote.core.BaseResponse
import com.yuzhny.mykis.domain.service.ServiceEntity
import com.yuzhny.mykis.domain.water.WaterMeterEntity

class GetWaterMeterResponse (
    success:Int,
    message: String,
    @Json(name = "water_meters")
    val waterMeters: List<WaterMeterEntity>
) : BaseResponse(success, message)