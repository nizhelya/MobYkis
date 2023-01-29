package com.yuzhny.mykis.data.cache.appartment

import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import kotlinx.coroutines.flow.Flow


interface AppartmentRepository {
    suspend fun addAppartment(appartment:AppartmentEntity)
    suspend fun updateAppartment(appartment: AppartmentEntity)
    suspend fun deleteAppartment(appartment: AppartmentEntity)
    fun getAppartment(addressId:Int): Flow<AppartmentEntity>
    fun getAppartments():Flow<List<AppartmentEntity>>
}