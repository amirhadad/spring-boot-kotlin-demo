package com.demo.security

import com.demo.domain.User
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.springframework.test.util.AssertionErrors.assertEquals
import org.springframework.test.util.AssertionErrors.assertTrue

object JwtUtilTest: Spek( {
    given("a user") {
        val user = User("testFirstName","testLastName")
        on("generating jwt token") {
            val token = JwtUtil().generateToken(user)
            it("generates a non-empty string") {
                assertTrue("Token is not empty", token.isNotEmpty())
            }
            it("jwt should be deserializable to user object") {
                val deserializedUser = JwtUtil().parseToken(token)
                assertEquals("first names match", deserializedUser?.firstName, user.firstName)
                assertEquals("last names match", deserializedUser?.lastName, user.lastName)

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
