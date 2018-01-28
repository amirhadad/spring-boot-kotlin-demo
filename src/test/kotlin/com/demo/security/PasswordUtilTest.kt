package com.demo.security

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.springframework.test.util.AssertionErrors.*


object PasswordUtilTest:Spek({
    given("a password") {
        val password = "TestPassword"
        val invalidPassword = "InvalidPassword"
        on("generating a hash") {
            val firstHashedPassword = PasswordUtil.getSaltedHash(password)
            val secondHashedPassword = PasswordUtil.getSaltedHash(password)
            it("each hash should be identical") {
                assertNotEquals("hashed passwords", firstHashedPassword, secondHashedPassword)
            }
            it("check function should return true if password is valid for all hashes") {
                assertTrue("new password checked against hash", PasswordUtil.check(password, firstHashedPassword))
                assertTrue("new password checked against hash", PasswordUtil.check(password, secondHashedPassword))
            }
            it("check function should return false if password is not valid") {
                assertNotEquals("new password checked against hash", PasswordUtil.check(invalidPassword, firstHashedPassword), true)
            }
        }
    }
})
