package com.yuzhny.mykis.domain.type

/**
 * Базовый класс для обработки ошибок / сбоев / исключений. */

sealed class Failure {
    object NetworkConnectionError : Failure()
    object ServerError : Failure()
    object AuthError : Failure()
    object TokenError : Failure()

    object EmailAlreadyExistError : Failure()
    object EmailNotRegisteredError : Failure()
    object CantSendEmailError : Failure()

    object AlreadyFriendError : Failure()
    object AlreadyRequestedFriendError : Failure()
    object ContactNotFoundError : Failure()

    object FlatAlreadyInDataBase : Failure()
    object IncorrectCode : Failure()

    object NoSavedAccountsError : Failure()

    object FilePickError : Failure()
}