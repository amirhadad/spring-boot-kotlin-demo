package com.demo.domain

import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {

	fun findByLastName(lastName: String): Iterable<User>
}
