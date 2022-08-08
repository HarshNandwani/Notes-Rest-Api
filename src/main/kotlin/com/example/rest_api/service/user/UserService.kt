package com.example.rest_api.service.user

import com.example.rest_api.model.entity.User

interface UserService {

    fun addUser(user: User)

    fun getUserByEmail(email: String)

}
