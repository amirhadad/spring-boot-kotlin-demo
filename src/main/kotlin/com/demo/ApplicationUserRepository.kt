package com.demo

import org.springframework.data.repository.CrudRepository

interface ApplicationUserRepository : CrudRepository<ApplicationUser, Long> {

	fun findByUsername(username: String): Iterable<ApplicationUser>
	fun findByEmail(email: String): Iterable<ApplicationUser>
}
