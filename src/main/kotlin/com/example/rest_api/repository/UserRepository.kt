package com.example.rest_api.repository

import com.example.rest_api.model.entity.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Long> {
    fun findUserByEmail(email: String?): User
}
