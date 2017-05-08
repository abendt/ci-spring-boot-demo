import groovy.json.JsonSlurper

def dockerHost = project.properties['dockerHost']
def dockerUrl = "http://$dockerHost:4243"

def listContainers() {
    def baseUrl = new URL("$dockerUrl/containers/json")

    def connection = baseUrl.openConnection()
    def result

    connection.with {
        doOutput = true
        requestMethod = 'GET'
        result = new JsonSlurper().parseText(content.text)
    }

    return result
}

def stopContainer() {
    if (dockerHost == '127.0.0.1') {
        println("local docker")

        println "docker rm -f ci-demo".execute().text
    } else {
        println("remote docker")

        println listContainers()

        def baseUrl = new URL("$dockerUrl/containers/ci-demo?force=true")

        def connection = baseUrl.openConnection()

        try {
            connection.with {
                doOutput = true
                requestMethod = 'DELETE'
                println content.text
            }
        } catch (Exception e) {
            e.printStackTrace()

            throw e
        }
    }
}

stopContainer()

