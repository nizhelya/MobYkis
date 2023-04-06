package com.yuzhny.mykis.data.remote.water.meter

import com.squareup.moshi.Json
import com.yuzhny.mykis.data.remote.core.BaseResponse
import com.yuzhny.mykis.domain.water.meter.WaterMeterEntity

class GetWaterMeterResponse (
    success:Int,
    message: String,
    @Json(name = "water_meters")
    val waterMeters: List<WaterMeterEntity>
) : BaseResponse(success, message)