package pappel

import pappel.database.Connection

external fun require(module:String):dynamic

open class Application : Router() {

    private val app: dynamic = express()
    override val expressRouter: dynamic = app

    private val dbConnections: MutableMap<String, Connection> = mutableMapOf()

    init {
        val mustacheExpress = require("mustache-express")

        app.engine("html", mustacheExpress())
        app.set("view engine", "html")
    }

    /**
     * Start listening on UNIX socket with [socketPath]
     */
    fun listen(socketPath: String, callback: (() -> Unit)? = null) {
        app.listen(socketPath) {
            callback?.invoke()
        }
    }

    /**
     * Start listening on [port]
     */
    fun listen(port: Int, callback: (() -> Unit)? = null) {
        app.listen(port) {
            callback?.invoke()
        }
    }

    /**
     * Start listening on [host]:[port]
     */
    fun listen(port: Int, host: String, callback: (() -> Unit)? = null) {
        app.listen(port, host) {
            callback?.invoke()
        }
    }

    /**
     * Set DB [connection] with [name]
     */
    fun setDBConnection(name: String, connection: Connection): Connection {
        dbConnections.put(name, connection)
        return connection
    }

    /**
     * Get DB connection with [name]
     */
    fun getDBConnection(name: String): Connection {
        return dbConnections[name]!!
    }

}