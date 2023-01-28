package com.yuzhny.mykis.data.cache.appartment

import com.yuzhny.mykis.data.dao.AppartmentDao
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppartmentRepositoryImpl @Inject constructor(
    private val appartmentDao: AppartmentDao
):AppartmentRepository {

    override fun addAppartment(appartments : List<AppartmentEntity>){
        appartmentDao.insertAppartment(appartments)
    }


}