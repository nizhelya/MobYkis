package com.yuzhny.mykis.data.remote.heat.reading

import com.squareup.moshi.Json
import com.yuzhny.mykis.data.remote.core.BaseResponse
import com.yuzhny.mykis.domain.heat.reading.HeatReadingEntity

class GetHeatReadingResponse (
    success:Int,
    message: String,
    @Json(name = "heat_readings")
    val heatReadings: List<HeatReadingEntity>
) : BaseResponse(success, message)