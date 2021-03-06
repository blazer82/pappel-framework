package demo

import async
import await
import pappel.Application
import pappel.database.Connection

class Application : Application() {

    /**
     * Setup database connection
     */
    val db = Connection("localhost", "test", "root", "root")

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
         * Register asset directory
         */
        useStaticDirectory("web/public")

        /**
         * Connect and setup database
         */
        async {
            try {
                await { db.connect() }

                await { model.user.sync(true) }

                val user1 = model.user.new()
                user1.username = "User 1"

                val user2 = model.user.new()
                user2.username = "User 2"

                await { model.user.save(user1) }
                await { model.user.save(user2) }
            }
            catch (e: Exception) {
                println("Error setting up database: ${e.message}")
            }
        }
    }

}