# Spring Boot Kotlin boilerplate

This is a Fork of [this repository](https://github.com/sdeleuze/spring-boot-kotlin-demo) and turning into a boilerplate project with a standard structure.

## Overview

This is a Spring Boot project with Kotlin and the following Spring tools are used: 

+ Spring Data JPA
+ Spring Security

## Project Structure

Following [spring.io](https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-structuring-your-code.html) suggested structure and [this stackoverflow post](https://stackoverflow.com/questions/42946220/package-structure-in-spring-entity-vs-model-vs-controller), the project structure is as follows:

	+- com
     	+- demo
         	+- Application.kt
         	|
         	+- domain
         	|   	+- User.kt
         	|   	+- UserRepository.kt
         	|		...
         	+- service
         	|   	+- UserService.kt
         	|		...
		+- web
         	 	+- controller
                	|	+- UserController.kt
             	|	...
             	+- filter
             	|	...
             	


## Prerequisits

+ IntelliJ IDEA 2017.1+
+ Kotlin plugin 1.1.x
+ [Kotlin based Gradle](https://github.com/gradle/kotlin-dsl) Configuration


## Launch

You can launch the application with by running:

		$ ./gradlew bootRun

This project uses `kotlin-spring` plugin to avoid requiring `open` modifier on proxified
classes and methods, see [this blog post](https://blog.jetbrains.com/kotlin/2016/12/kotlin-1-0-6-is-here/) for more details.


This project is Apache 2.0 licensed.