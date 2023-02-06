package com.yuzhny.mykis.data.cache.appartment

import com.yuzhny.mykis.data.dao.AppartmentDao
import com.yuzhny.mykis.data.remote.service.ApiService
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import kotlinx.coroutines.flow.Flow
//import java.util.concurrent.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppartmentRepositoryImpl @Inject constructor(
    private val appartmentDao: AppartmentDao,
    private val apiService: ApiService
):AppartmentRepository {

    override suspend fun addAppartment(appartments :List<AppartmentEntity>){
        appartmentDao.insertAppartment(appartments)
    }

    override suspend fun updateAppartment(appartment: AppartmentEntity) {
        appartmentDao.updateAppartment(appartment)
    }

    override suspend fun deleteAppartment(appartment: AppartmentEntity) {
        appartmentDao.deleteAppartment(appartment)
    }

    override fun getAppartment(addressId: Int): List<AppartmentEntity> {
        return appartmentDao.getAppartment(addressId)
    }

//    override suspend fun getAppartment(): Flow<List<AppartmentEntity>> {
//        return  appartmentDao.getAppartments()
//    }


}