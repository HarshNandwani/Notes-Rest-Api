package com.example.rest_api.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BaseController {

    @GetMapping
    fun index(): String {
        return "index"
    }

    @GetMapping("/hello")
    fun hello(): String {
        return "Hello world!"
    }

}
