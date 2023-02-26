package com.yuzhny.mykis.domain.family

import com.yuzhny.mykis.domain.family.request.FamilyBooleanInt
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure

interface FamilyRepository {
    fun getFamilyFromFlat(params: FamilyBooleanInt):Either<Failure , List<FamilyEntity>>
}