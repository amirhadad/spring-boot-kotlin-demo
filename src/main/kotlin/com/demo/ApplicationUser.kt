package com.demo

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class ApplicationUser(
		val firstName: String,
		val lastName: String,
		val username: String,
		val password: String,
		val email: String,

		@Id @GeneratedValue
		val id: Long = -1)
