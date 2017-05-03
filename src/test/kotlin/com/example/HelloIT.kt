package com.example

import io.restassured.RestAssured.`when`
import io.restassured.RestAssured.given
import org.hamcrest.Matchers.equalTo
import org.junit.Test

class HelloIT {

    val url = "http://localhost:8080"

    @Test
    fun getHello() {
        given().basePath(url)
                `when`().
                get("/").
                then().
                statusCode(200).
                body(equalTo("Hello World"))
    }
}

