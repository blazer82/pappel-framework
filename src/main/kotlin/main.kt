import demo.Application

fun main(args: Array<String>) {

    /**
     * Print log message on startup
     */
    println("Node.js service built with Kotlin")

    /**
     * Initialize application
     */
    val application = Application()

    /**
     * Register global request handler
     */
    application.onRequest {
        request, _, next ->
        println("Incoming request: ${request.path}")
        next()
    }

    /**
     * Listen on port 3000
     */
    application.listen(3000).then {
        println("Listening on port 3000")
    }
}