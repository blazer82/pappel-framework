import demo.Router
import pappel.Application

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
     * Register demo router
     */
    application.use("/", Router())

    /**
     * Listen on port 3000
     */
    application.listen(3000, {
        println("Listening on port 3000")
    })
}