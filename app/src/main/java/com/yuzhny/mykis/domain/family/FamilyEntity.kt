package com.yuzhny.mykis.domain.family

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.util.Date

@Entity
data class FamilyEntity(
    @PrimaryKey(autoGenerate = true)
    @Json(name = "rec_id")
    @ColumnInfo(name = "rec_id")
    var recId :Int ,
    @Json(name = "address_id")
    @ColumnInfo(name = "address_id")
    var addressId :Int ,

    var fistName :String = "Unknown",
    var lastName :String = "Unknown" ,
    var surName :String = "Unknown" ,

    var born :Date,

    var sex:String = "Unknown" ,

    var phone:String = "Unknown"
)
