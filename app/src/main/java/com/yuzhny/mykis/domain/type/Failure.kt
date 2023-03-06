package com.yuzhny.mykis.domain.type

/**
 * Базовый класс для обработки ошибок / сбоев / исключений. */

sealed class Failure {
    object NetworkConnectionError : Failure()
    object ServerError : Failure()
    object TokenError : Failure()
    object CantSendEmailError : Failure()

    object FlatAlreadyInDataBase : Failure()
    object IncorrectCode : Failure()
    object MissingFields : Failure()
    object FailDeleteFlat: Failure()

    object NoSavedAccountsError : Failure()

    object FilePickError : Failure()
}