package com.yuzhny.mykis.domain.address

import androidx.room.ColumnInfo
import com.squareup.moshi.Json

data class AddressEntity(
    @Json(name = "block_id")
    val blockId:Int=2,
    val block:String="Unknown",
    @Json(name = "street_id")
    val streetId:Int=0,
    val street:String="Unknown",
    val houseId:Int=0,
    val house:String="Unknown",
    val addressId:Int=0
)