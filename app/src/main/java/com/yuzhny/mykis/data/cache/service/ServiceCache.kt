package com.yuzhny.mykis.data.cache.service

import com.yuzhny.mykis.domain.family.FamilyEntity
import com.yuzhny.mykis.domain.service.ServiceEntity

interface ServiceCache {
    fun addService(service:List<ServiceEntity>)
    fun getServiceFromFlat(addressId:Int):List<ServiceEntity>
    fun deleteServiceFromFlat(addressId:List<Int>)
}