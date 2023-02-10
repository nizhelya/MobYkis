package com.yuzhny.mykis.data.cache.appartment

import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure
import kotlinx.coroutines.flow.Flow


interface AppartmentRepository {
    suspend fun addAppartment(appartment:List<AppartmentEntity>)
    suspend fun updateAppartment(appartment: AppartmentEntity)
    suspend fun deleteAppartment(appartment: AppartmentEntity)
    suspend fun getAppartment(addressId: Int):List<AppartmentEntity>
}