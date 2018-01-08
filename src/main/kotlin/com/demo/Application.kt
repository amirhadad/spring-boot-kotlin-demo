package com.demo

import com.demo.domain.User
import com.demo.domain.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Application {

	private val log = LoggerFactory.getLogger(Application::class.java)

	@Bean
	fun init(repository: UserRepository) = CommandLineRunner {
			// save a couple of users
			repository.save(User("Jack", "Bauer"))
			repository.save(User("Chloe", "O'Brian"))
			repository.save(User("Kim", "Bauer"))
			repository.save(User("David", "Palmer"))
			repository.save(User("Michelle", "Dessler"))

			// fetch all users
			log.info("Users found with findAll():")
			log.info("-------------------------------")
			repository.findAll().forEach { log.info(it.toString()) }
			log.info("")

			// fetch an individual user by ID
			val user = repository.findById(1L)
			user.ifPresent {
				log.info("User found with findOne(1L):")
				log.info("--------------------------------")
				log.info(it.toString())
				log.info("")
			}

			// fetch users by last name
			log.info("User found with findByLastName('Bauer'):")
			log.info("--------------------------------------------")
			repository.findByLastName("Bauer").forEach { log.info(it.toString()) }
			log.info("")
	}

}

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
