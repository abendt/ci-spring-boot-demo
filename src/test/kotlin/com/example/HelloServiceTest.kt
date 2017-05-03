package com.example

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class HelloServiceTest {

    val sut = HelloService()

    @Test
    fun canSayHelloTo() {
        assertThat(sut.sayHelloTo("World")).isEqualTo("Hello World!")
    }

}