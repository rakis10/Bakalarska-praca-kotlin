package com.example.kotlinbp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinBpApplication

fun main(args: Array<String>) {
	runApplication<KotlinBpApplication>(*args)
}
