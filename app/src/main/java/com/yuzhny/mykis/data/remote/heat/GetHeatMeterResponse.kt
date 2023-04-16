package com.yuzhny.mykis.data.remote.heat

import com.squareup.moshi.Json
import com.yuzhny.mykis.data.remote.core.BaseResponse
import com.yuzhny.mykis.domain.heat.meter.HeatMeterEntity
import com.yuzhny.mykis.domain.water.meter.WaterMeterEntity

class GetHeatMeterResponse (
    success:Int,
    message: String,
    @Json(name = "heat_meters")
    val heatMeters: List<HeatMeterEntity>
) : BaseResponse(success, message)