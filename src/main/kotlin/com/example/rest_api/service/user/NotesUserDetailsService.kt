package com.example.rest_api.service.user

import com.example.rest_api.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class NotesUserDetailsService: UserDetailsService {

    @Autowired
    private lateinit var dao: UserRepository

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = dao.findUserByEmail(username)
        println("user: $user")
        return User(user.email, user.password, emptyList())
    }

}
