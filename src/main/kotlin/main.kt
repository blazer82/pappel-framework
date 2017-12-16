import demo.Model
import demo.Router
import pappel.Application
import pappel.database.Connection

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
     * Setup database connection
     */
    val db = Connection("localhost", "test", "root", "root")
    db.connect {
        error ->
        if (error != null) {
            println("ERROR: Failed to connect to database. ${error.message}")
        }
        else {
            println("Database connection established.")

            /**
             * Register user model
             */
            val model = Model(db)

            /**
             * Create table
             */
            model.user.sync(true) {
                val user = model.user.new()
                user.username = "test"

                model.user.save(user)
            }
        }
    }

    /**
     * Listen on port 3000
     */
    application.listen(3000, {
        println("Listening on port 3000")
    })
}