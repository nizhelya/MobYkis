package com.yuzhny.mykis.data.cache.user

import com.yuzhny.mykis.domain.type.Either
import com.yuzhny.mykis.domain.type.Failure
import com.yuzhny.mykis.domain.user.UserEntity
import javax.inject.Inject

class UserCacheImpl @Inject constructor() :UserCache{
    override fun getCurrentUser(): Either<Failure, List<UserEntity>> {
        return Either.Right(listOf(UserEntity(1,"Petya","example@gmail.com","url","543ger245"),
            UserEntity(1,"Petya","example@gmail.com","url","543ger245")
            ))
    }
}