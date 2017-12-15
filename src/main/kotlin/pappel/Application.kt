package pappel

import pappel.http.Request
import pappel.http.Response

external fun require(module:String):dynamic

class Application(external private val app: dynamic) {

    init {
        val mustacheExpress = require("mustache-express")

        app.engine("html", mustacheExpress())
        app.set("view engine", "html")
    }

    /**
     * Handle GET request for [path]
     */
    fun get(path: String, callback: (request: Request, response: Response) -> Unit) {
        app.get(path) {
            req, res -> callback.invoke(Request(req), Response(res))
        }
    }

    /**
     * Start listening on UNIX socket with [socketPath]
     */
    fun listen(socketPath: String) {
        app.listen(socketPath)
    }

    /**
     * Start listening on UNIX socket with [socketPath]
     */
    fun listen(socketPath: String, callback: (() -> Unit)) {
        app.listen(socketPath) {
            callback.invoke()
        }
    }

    /**
     * Start listening on [port]
     */
    fun listen(port: Int) {
        app.listen(port)
    }

    /**
     * Start listening on [port]
     */
    fun listen(port: Int, callback: () -> Unit) {
        app.listen(port) {
            callback.invoke()
        }
    }

    /**
     * Start listening on [host]:[port]
     */
    fun listen(port: Int, host: String) {
        app.listen(port, host)
    }

    /**
     * Start listening on [host]:[port]
     */
    fun listen(port: Int, host: String, callback: () -> Unit) {
        app.listen(port, host) {
            callback.invoke()
        }
    }

    /**
     * Register middleware globally
     */
    fun onRequest(callback: (request: Request, response: Response, next: () -> Unit) -> Unit) {
        app.use {
            req, res, n -> callback.invoke(Request(req), Response(res), n as () -> Unit)
        }
    }

}