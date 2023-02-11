package com.yuzhny.mykis.domain.user


data class UserEntity(
    val id :Int,
    val name :String="Unknown",
    val email :String="Unknown",
    val image :String="Unknown",
    val token:String="Unknown"
)