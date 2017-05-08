import groovy.json.JsonSlurper

import java.sql.DriverManager

dockerHost = project.properties['dockerHost']
dockerUrl = "http://$dockerHost:4243"

URLConnection connectTo(String urlString) {
    def url = new URL(urlString)

    def connection = url.openConnection()
    connection.setConnectTimeout(2000)
    connection.setReadTimeout(2000)

    connection
}

def listContainers() {
    def connection = connectTo("$dockerUrl/containers/json")

    def result

    connection.with {
        doOutput = true
        requestMethod = 'GET'
        result = new JsonSlurper().parseText(content.text)
    }

    result
}

def stopContainer() {
    if (dockerHost == '127.0.0.1') {
        localStopContainer()
    } else {
        remoteStopContainer()
    }
}

def localStopContainer() {
    println("local docker")

    println "docker rm -f ci-demo".execute().text
}

def remoteStopContainer() {
    println("remote docker")

    def containers = listContainers()
    def names = containers.collectMany { it['Names'] }

    println(names)

    if (!names.contains("/ci-demo")) {
        return
    }

    println 'remove previous ci-demo container'

    def connection = connectTo("$dockerUrl/containers/ci-demo?force=true")

    connection.with {
        doOutput = true
        requestMethod = 'DELETE'
    }
}

stopContainer()