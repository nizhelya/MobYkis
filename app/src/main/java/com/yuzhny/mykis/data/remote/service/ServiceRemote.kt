package com.yuzhny.mykis.data.remote.service

import com.yuzhny.mykis.domain.family.FamilyEntity
import com.yuzhny.mykis.domain.service.ServiceEntity
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure

interface ServiceRemote {
    fun getFlatServices(addressId:Int , houseId:Int , qty:Byte , service:Byte , userId :Int , token : String): Either<Failure, List<ServiceEntity>>
}