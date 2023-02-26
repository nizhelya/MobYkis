package com.yuzhny.mykis.domain.family

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import java.util.Date

@Entity(tableName = "family")
data class FamilyEntity(
    @PrimaryKey(autoGenerate = false)
    @Json(name = "rec_id")
    @ColumnInfo(name = "rec_id")
    var recId :Int ,
    @Json(name = "address_id")
    @ColumnInfo(name = "address_id")
    var addressId :Int = 0 ,
    var address :String = "Unknown" ,
    var rodstvo :String = "Unknown" ,
    @Json(name = "firstname")
    @ColumnInfo(name = "firstname")
    var fistname :String = "Unknown",
    @Json(name = "lastname")
    @ColumnInfo(name = "lastname")
    var lastname :String = "Unknown" ,
    @Json(name = "surname")
    @ColumnInfo(name = "surname")
    var surname :String = "Unknown" ,

    var born :String ="Unknown",

    var sex:String = "Unknown" ,

    var phone:String = "Unknown"
)
