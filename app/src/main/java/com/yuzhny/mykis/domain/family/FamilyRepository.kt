package com.yuzhny.mykis.domain.family

import com.yuzhny.mykis.domain.family.request.BooleanInt
import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure

interface FamilyRepository {
    fun getFamilyFromFlat(params: BooleanInt):Either<Failure , List<FamilyEntity>>
}