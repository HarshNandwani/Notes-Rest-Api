package com.example.rest_api.controller

import com.example.rest_api.model.entity.User
import com.example.rest_api.model.response.RegistrationResponse
import com.example.rest_api.service.user.UserService
import com.example.rest_api.utils.Constants
import org.hibernate.exception.ConstraintViolationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController {

    @Autowired
    private lateinit var userService: UserService

    @PostMapping("/register")
    fun register(@RequestBody user: User): ResponseEntity<RegistrationResponse> {
        var message = Constants.ERR_REGISTRATION_FAILED
        lateinit var status: HttpStatus
        try {
            userService.addUser(user)
            status = HttpStatus.OK
            message = Constants.MSG_REGISTRATION_SUCCESS
        } catch (e: Exception) {
            println("Unable to create user: ${e.cause} ${e.message} ${e.stackTrace}")
            if (e is ConstraintViolationException || e.message?.contains("ConstraintViolationException") == true) {
                message = Constants.ERR_EMAIL_EXISTS
            }
            status = HttpStatus.CONFLICT
        }
        return ResponseEntity.status(status).body(
            RegistrationResponse(message)
        )
    }

}
