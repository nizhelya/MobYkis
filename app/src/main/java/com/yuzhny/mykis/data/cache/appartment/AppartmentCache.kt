package com.yuzhny.mykis.data.cache.appartment

import com.yuzhny.mykis.domain.appartment.AppartmentEntity


interface AppartmentCache {
    fun addAppartment(appartment:List<AppartmentEntity>)
    fun getAppartments():List<AppartmentEntity>
    //    suspend fun updateAppartment(appartment: AppartmentEntity)
    //    suspend fun deleteAppartment(appartment: AppartmentEntity)
}