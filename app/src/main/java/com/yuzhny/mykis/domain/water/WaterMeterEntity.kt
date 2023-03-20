package com.yuzhny.mykis.domain.water

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "water_meter")
data class WaterMeterEntity(
    @PrimaryKey(autoGenerate = false)
    @Json(name = "vodomer_id")
    val vodomerId  : Int = 0,
    @Json(name = "dvodomer_id")
    val dvodomerId : Int = 0,
    @Json(name = "address_id")
    val addressId  : Int = 0,
    val nomer      : String = "Unknown",
    val model      : String = "Unknown",
    val st         : Byte = 1,
    val voda       : String = "Unknown",
    val place      : String = "Unknown",
    val position   : String = "Unknown",
    val sdate      : String = "Unknown",
    val fpdate     : String = "Unknown",
    val pdate      : String = "Unknown",
    val pp         : Byte = 0,
    val zdate      : String = "Unknown",
    val avg        : Byte = 0,
    val spisan     : Byte = 0 ,
    val paused     : Byte = 0,
    @Json(name = "data_spis")
    val dataSpis   : String = "Unknown"
)