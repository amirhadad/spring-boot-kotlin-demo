import io.spring.gradle.dependencymanagement.internal.pom.Pom
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.api.plugins.ExtensionAware

import org.junit.platform.gradle.plugin.EnginesExtension
import org.junit.platform.gradle.plugin.FiltersExtension
import org.junit.platform.gradle.plugin.JUnitPlatformExtension

apply {plugin("maven")}
apply {plugin("java")}
buildscript {
	repositories {
		mavenCentral()
		maven("https://repo.spring.io/milestone")
	}

	dependencies {
		classpath("org.springframework.boot:spring-boot-gradle-plugin:2.0.0.M7")
		classpath("org.junit.platform:junit-platform-gradle-plugin:1.0.2")
	}
}


apply {
	plugin("org.springframework.boot")
	plugin("org.junit.platform.gradle.plugin")
}


plugins {
	val kotlinVersion = "1.1.61"
	id("org.jetbrains.kotlin.jvm") version kotlinVersion
	id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
	id("org.jetbrains.kotlin.plugin.jpa") version kotlinVersion
	id("io.spring.dependency-management") version "1.0.3.RELEASE"
}

version = "0.0.1-SNAPSHOT"

tasks {
	withType<KotlinCompile> {
		kotlinOptions {
			jvmTarget = "1.8"
			freeCompilerArgs = listOf("-Xjsr305=strict")
		}
	}

    withType<GradleBuild>{
        finalizedBy("publishToMavenLocal")
    }

}

repositories {
	mavenCentral()
	maven("http://repo.spring.io/milestone")
    maven("http://dl.bintray.com/jetbrains/spek")
}

dependencies {
	compile("org.springframework.boot:spring-boot-starter-web")
	compile("org.springframework.boot:spring-boot-starter-data-jpa")
	compile("org.springframework.boot:spring-boot-starter-security")
	compile("com.h2database:h2")
	compile("org.jetbrains.kotlin:kotlin-stdlib")
	compile("io.jsonwebtoken:jjwt:0.2")
	compile("org.jetbrains.kotlin:kotlin-reflect")
	compile("com.fasterxml.jackson.module:jackson-module-kotlin")



    testCompile("org.junit.platform:junit-platform-runner:1.0.3")
    testCompile("org.jetbrains.spek:spek-api:1.1.5")
	testRuntime("org.jetbrains.spek:spek-junit-platform-engine:1.1.5")
	testCompile("org.springframework.boot:spring-boot-starter-test") {
		exclude(module = "junit")
	}
	testCompile("org.junit.jupiter:junit-jupiter-api")
	testRuntime("org.junit.jupiter:junit-jupiter-engine")
}


repositories {
    jcenter()
}

dependencies {
    compile("com.google.guava:guava:13.0.1")
    compile("joda-time:joda-time:2.1")

    testCompile("junit:junit:4.11")
    testCompile("org.mockito:mockito-core:1.9.5")
}

tasks {
    "writeNewPom" {
        doLast {
            project.withConvention(MavenPluginConvention::class) {
                pom {
                    project {
                        groupId = "com.demo"
                        artifactId = "katlin-spring-boot"
                        version = "0.0.1-SNAPSHOT"
                        withGroovyBuilder {
                            "inceptionYear"("2018")
                            "licenses" {
                                "license" {
                                    "name"("The Apache Software License, Version 2.0")
                                    "url"("http://www.apache.org/licenses/LICENSE-2.0.txt")
                                    "distribution"("repo")
                                }
                            }
                        }
                    }
                }
            }.writeTo("$buildDir/pom.xml")
        }
    }
}


configure<JUnitPlatformExtension> {
    filters {
        engines {
            include("spek", "junit-jupiter", "junit-vintage")
        }
    }
}

// extension for configuration
fun JUnitPlatformExtension.filters(setup: FiltersExtension.() -> Unit) {
	when (this) {
		is ExtensionAware -> extensions.getByType(FiltersExtension::class.java).setup()
		else -> throw Exception("${this::class} must be an instance of ExtensionAware")
	}
}
fun FiltersExtension.engines(setup: EnginesExtension.() -> Unit) {
	when (this) {
		is ExtensionAware -> extensions.getByType(EnginesExtension::class.java).setup()
		else -> throw Exception("${this::class} must be an instance of ExtensionAware")
	}
}

tasks.withType<Jar> {
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
}