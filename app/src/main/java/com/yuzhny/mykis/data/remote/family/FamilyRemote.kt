package com.yuzhny.mykis.data.remote.family

import com.yuzhny.mykis.domain.family.FamilyEntity
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure

interface FamilyRemote {
    fun getFamilyFromFlat(addressId:Int , userId :Int , token : String):Either<Failure , List<FamilyEntity>>
}