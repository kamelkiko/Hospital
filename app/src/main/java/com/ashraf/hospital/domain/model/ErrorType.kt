package com.ashraf.hospital.domain.model

open class AbException(message: String?) : Exception(message)

open class AuthException(message: String?) : AbException(message)
class UnAuthException(message: String?) : AuthException(message)
class UserAlreadyExistException(message: String?) : AuthException(message)
class UserNotFoundException(message: String?) : AuthException(message)

open class NetworkException(message: String?) : AbException(message)
class NoInternetConnectionException(message: String?) : NetworkException(message)

open class ServerException(message: String?) : AbException(message)
class ServerErrorException(message: String?) : ServerException(message)

class NotFoundException(message: String?) : AbException(message)

class UnKnownErrorException(message: String?) : AbException(message)
class NullResultException(message: String?) : AbException(message)

open class ValidationException(message: String?) : AbException(message)
class EmptyInputException(message: String?) : ValidationException(message)
class InvalidEmailException(message: String?) : ValidationException(message)
class InvalidPasswordException(message: String?) : ValidationException(message)
class InvalidFirstNameException(message: String?) : ValidationException(message)
class InvalidLastNameException(message: String?) : ValidationException(message)
class InvalidAddressException(message: String?) : ValidationException(message)
class InvalidPhoneNumberException(message: String?) : ValidationException(message)
