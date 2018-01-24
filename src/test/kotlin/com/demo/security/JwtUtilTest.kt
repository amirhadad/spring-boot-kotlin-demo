package com.demo.security

import com.demo.domain.User
import org.hibernate.validator.internal.util.Contracts
import org.hibernate.validator.internal.util.Contracts.assertNotEmpty
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.springframework.test.util.AssertionErrors.assertTrue


object JwtUtilTest: Spek( {
    given("a user") {
        val user = User("testFirstName","testLastName")
        on("generating jwt token") {
            val token = JwtUtil().generateToken(user)
            it("generate a string") {
                assertTrue("Token is not empty", token.isNotEmpty())
            }
        }

    }

//    var user: User
//    fun init() {
//        user.firstName = "TestFirstName"
//        user.lastName = "TestLastName"
//    }
//
//    fun parseToken() {
//        val token = JwtUtil().generateToken(user)
//        val deserialisedToken:User? = JwtUtil().parseToken(token)
//        print(deserialisedToken)
//    }
//
//    @Test
//    fun generateToken() {
//    }

})
