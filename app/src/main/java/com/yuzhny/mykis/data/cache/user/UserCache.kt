package com.yuzhny.mykis.data.cache.user

import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure
import com.yuzhny.mykis.domain.user.UserEntity

interface UserCache {
    fun getCurrentUser(): Either<Failure,UserEntity>
}