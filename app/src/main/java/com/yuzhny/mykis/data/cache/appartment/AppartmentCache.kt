package com.yuzhny.mykis.data.cache.appartment

import com.yuzhny.mykis.domain.appartment.AppartmentEntity


interface AppartmentCache {
    fun addAppartmentByUser(appartment:List<AppartmentEntity>)
    fun getAppartmentsByUser():List<AppartmentEntity>
    fun deleteAllAppartments()
    fun deleteFlat(addressId: Int)
}