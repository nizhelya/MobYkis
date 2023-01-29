package com.yuzhny.mykis.data.cache.appartment

import com.yuzhny.mykis.domain.appartment.AppartmentEntity


interface AppartmentRepository {
    fun addAppartment(appartment:List<AppartmentEntity>)
}