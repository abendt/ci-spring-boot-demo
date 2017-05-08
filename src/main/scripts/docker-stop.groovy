def dockerHost = project.properties['dockerHost']

if (dockerHost == '127.0.0.1') {
    println("local docker")

    println "docker rm -f ci-demo".execute().text
} else {
    println("remote docker")

    def baseUrl = new URL("http://" + dockerHost + ":4243/containers/ci-demo?force=true")

    def connection = baseUrl.openConnection()
    connection.with {
        doOutput = true
        requestMethod = 'DELETE'
        println content.text
    }
}

