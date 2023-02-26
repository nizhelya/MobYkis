package com.yuzhny.mykis.data.cache.user

import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure
import com.yuzhny.mykis.domain.user.UserEntity
import javax.inject.Inject

class UserCacheImpl @Inject constructor() :UserCache{
    override fun getCurrentUser(): Either<Failure,UserEntity> {
        return Either.Right(UserEntity( 8,"Petya","example@gmail.com","url","543ger245"))
    }
}