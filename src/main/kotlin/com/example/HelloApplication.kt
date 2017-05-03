package com.example

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class HelloApplication {

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(HelloApplication::class.java, *args)
        }
    }
}
