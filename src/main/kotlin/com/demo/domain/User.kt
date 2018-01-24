package com.demo.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class User(
		var firstName: String = "",
		var lastName: String = "",
		@Id @GeneratedValue
		var id: Long = -1)
