package com.demo.api.controller

import com.demo.domain.User
import com.demo.domain.UserRepository
import io.jsonwebtoken.Claims
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val repository: UserRepository) {

    @GetMapping("/users")
    fun findAll(): MutableIterable<User> {
//        var claims: Claims = Claims()

        val usersIterable :MutableIterable<User>  = repository.findAll()
        usersIterable.forEach{user ->
            //user.firstName = "TESTTEST"
        }
        return usersIterable
    }
    @GetMapping("/users/{lastName}")
    fun findByLastName(@PathVariable lastName:String)
            = repository.findByLastName(lastName)

}