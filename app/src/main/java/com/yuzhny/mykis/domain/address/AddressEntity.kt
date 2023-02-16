package com.yuzhny.mykis.domain.address

data class AddressEntity(
    val blockId:Int=0,
    val block:String="Unknown",
    val houseId:Int=0,
    val house:String="Unknown",
    val addressId:Int=0
)