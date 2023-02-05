package com.yuzhny.mykis.data.cache.appartment

import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import kotlinx.coroutines.flow.Flow


interface AppartmentRepository {
    suspend fun addAppartment(appartment:List<AppartmentEntity>)
    suspend fun updateAppartment(appartment: AppartmentEntity)
    suspend fun deleteAppartment(appartment: AppartmentEntity)
    suspend fun getAppartment(addressId:Int): Flow<AppartmentEntity>
    suspend fun getAppartments():Flow<List<AppartmentEntity>>
}