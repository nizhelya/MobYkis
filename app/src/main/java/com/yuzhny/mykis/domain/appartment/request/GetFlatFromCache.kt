package com.yuzhny.mykis.domain.appartment.request

import com.yuzhny.mykis.data.cache.appartment.AppartmentCache
import com.yuzhny.mykis.domain.appartment.AppartmentEntity
import com.yuzhny.mykis.domain.appartment.AppartmentRepository
import com.yuzhny.mykis.domain.interactor.UseCase
import javax.inject.Inject

//class GetFlatFromCache  @Inject constructor(
//    private val appartmentCache: AppartmentCache
//) : UseCase<AppartmentEntity, Int>() {
//
//    override suspend fun run(params:Int) = appartmentCache.getFl(params)
//}