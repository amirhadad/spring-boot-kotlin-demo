package com.demo.api.controler

import com.demo.domain.ApplicationUserRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ApplicationUserController(private val repository: ApplicationUserRepository) {

	@GetMapping("/app/users")
	fun findAll() = repository.findAll()

	@GetMapping("/app/users/{lastName}")
	fun findByUsername(@PathVariable username:String)
			= repository.findByUsername(username)
	@GetMapping("/app/users/{email}")
	fun findByEmail(@PathVariable email:String)
			= repository.findByEmail(email)
}