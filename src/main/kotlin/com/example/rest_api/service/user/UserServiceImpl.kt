package com.example.rest_api.service.user

import com.example.rest_api.repository.UserRepository
import com.example.rest_api.model.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserService {

    @Autowired
    private lateinit var dao: UserRepository

    override fun addUser(user: User) {
        dao.save(user)
    }

    override fun getUserByEmail(email: String) {
        dao.findUserByEmail(email)
    }
}
