package pappel

external fun require(module:String):dynamic

class Application : Router() {

    private val app: dynamic = express()
    override val expressRouter: dynamic = app

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

}