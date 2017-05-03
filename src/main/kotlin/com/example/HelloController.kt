package com.example

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController(
        val service: HelloService
) {

    @RequestMapping("/")
    fun index(): String {
        return service.sayHelloTo("Spring Boot")
    }

}

