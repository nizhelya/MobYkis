package com.yuzhny.mykis.data.cache.appartment

import com.yuzhny.mykis.data.cache.dao.AppartmentDao
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
//import java.util.concurrent.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppartmentCacheImpl @Inject constructor(
    private val appartmentDao: AppartmentDao
):AppartmentCache {


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

    override fun getAppartmentById(addressId: Int): AppartmentEntity {
        return appartmentDao.getFlatById(addressId)
    }


}