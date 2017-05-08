package com.example

import io.restassured.RestAssured
import io.restassured.RestAssured.given
import org.hamcrest.Matchers.equalTo
import org.junit.Before
import org.junit.Test

class HelloIT {

    @Before
    fun setup() {
        val host = System.getProperty("dockerHost", "localhost")

        val url = "http://$host"

        RestAssured.port = 18080
        RestAssured.baseURI = url
    }

    @Test
    fun getHello() {
        given().
                `when`().
                get("/").
                then().
                statusCode(200).
                body(equalTo("Hello Spring Boot!"))
    }
}

