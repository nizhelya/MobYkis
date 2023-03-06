package com.yuzhny.mykis.data.cache.appartment

import com.yuzhny.mykis.data.dao.AppartmentDao
import com.yuzhny.mykis.data.remote.service.ApiService
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
//import java.util.concurrent.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppartmentCacheImpl @Inject constructor(
    private val appartmentDao: AppartmentDao
):AppartmentCache {

//    override suspend fun updateAppartment(appartment: AppartmentEntity) {
//        appartmentDao.updateAppartment(appartment)
//    }
//
//    override suspend fun deleteAppartment(appartment: AppartmentEntity) {
//        appartmentDao.deleteAppartment(appartment)
//    }
//
//    override suspend fun getAppartment(): Flow<List<AppartmentEntity>> {
//        return  appartmentDao.getAppartments()
//    }

    override fun addAppartmentByUser(appartment: List<AppartmentEntity>) {
        appartmentDao.addAppartmentByUser(appartment)
    }

    override fun getAppartmentsByUser(): List<AppartmentEntity> {
        return appartmentDao.getAppartmentsByUser()
    }

    override fun deleteAllAppartments() {
        appartmentDao.deleteAllAppartments()
    }

    override fun deleteFlat(addressId: Int) {
        appartmentDao.deleteFlat(addressId)
    }


}