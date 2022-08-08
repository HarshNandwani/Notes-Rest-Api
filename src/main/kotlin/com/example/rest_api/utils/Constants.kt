package com.example.rest_api.utils

import com.auth0.jwt.algorithms.Algorithm

object Constants {

    const val PARAM_EMAIL = "email"
    const val PARAM_PASS = "password"

    // TODO: Check if its safe to keep key here or to move somewhere else
    private const val SECRET_KEY = "b36c22c54143d24a9f9b2b9879c5bdf2e549dfc696e7484b3eb254e7d5fbbd93"
    val jwtSigningAlgorithm: Algorithm = Algorithm.HMAC256(SECRET_KEY)

    const val ERR_EMPTY_TOKEN = "You are not authorized"
    const val ERR_INVALID_TOKEN = "Invalid token"
    const val ERR_REGISTRATION_FAILED = "Registration Failed"
    const val ERR_EMAIL_EXISTS = "Email already exists"
    const val MSG_REGISTRATION_SUCCESS = "Registration Successful"

    const val NOTES_END_POINT = "/api/notes"
}
