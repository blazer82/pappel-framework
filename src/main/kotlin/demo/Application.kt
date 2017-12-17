package demo

import pappel.Application
import pappel.database.Connection

class Application : Application() {

    /**
     * Setup database connection
     */
    val db = setDBConnection("default", Connection("localhost", "test", "root", "root"))

    /**
     * Register user model
     */
    val model = Model(db)

    init {

        /**
         * Register router
         */
        use("/", Router())

        /**
         * Connect DB and startup application
         */
        db.connect {
            error ->
            if (error != null) {
                println("ERROR: Failed to connect to database. ${error.message}")
            }
            else {
                println("Database connection established.")

                /**
                 * Create table
                 */
                model.user.sync(true) {

                    /**
                     * Create two users
                     */
                    val user1 = model.user.new()
                    user1.username = "User 1"
                    model.user.save(user1)

                    val user2 = model.user.new()
                    user2.username = "User 2"
                    model.user.save(user2)
                }
            }
        }
    }

}