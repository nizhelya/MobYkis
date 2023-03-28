package com.yuzhny.mykis.data.cache.service

import com.yuzhny.mykis.domain.family.FamilyEntity
import com.yuzhny.mykis.domain.service.ServiceEntity

interface ServiceCache {
    fun addService(service:List<ServiceEntity>)
    fun getServiceFromFlat(addressId:Int , service: String):List<ServiceEntity>
    fun deleteServiceFromFlat(addressId:List<Int>)
    suspend fun getTotalDebt(addressId: Int):ServiceEntity
}