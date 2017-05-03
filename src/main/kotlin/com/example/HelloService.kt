package com.example

import org.springframework.stereotype.Service

@Service
class HelloService {

    fun sayHelloTo(name: String) = "Hello $name!"

}

