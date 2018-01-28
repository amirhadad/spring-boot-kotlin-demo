package com.demo.security

import com.demo.domain.User
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component

class JwtUtil {

    @Value("\${jwt.secret}")
    private val secret: String? = "Doesn't come from yml properties file!"

    /**
     * Tries to parse specified String as a JWT token. If successful, returns User object with username, id and role prefilled (extracted from token).
     * If unsuccessful (token is invalid or not containing all required user properties), simply returns null.
     *
     * @param token the JWT token to parse
     * @return the User object extracted from specified token or null if a token is invalid.
     */
    fun parseToken(token: String): User? {
        try {
            val body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .body

            var u = User()
            u.firstName = body.subject
            u.id = java.lang.Long.parseLong(body["userId"] as String)
            u.lastName = body["lastName"] as String

            return u

        } catch (e: JwtException) {
            return null
        } catch (e: ClassCastException) {
            return null
        }

    }

    /**
     * Generates a JWT token containing username as subject, and userId and role as additional claims. These properties are taken from the specified
     * User object. Tokens validity is infinite.
     *
     * @param u the user for which the token will be generated
     * @return the JWT token
     */
    fun generateToken(u: User): String {
        val claims = Jwts.claims().setSubject(u.firstName)
        claims.put("userId", u.id.toString() + "")
        claims.put("lastName", u.lastName)

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact()
    }
}