package framework.express

class Application(external private val app: dynamic) {

    /**
     * Handle GET request for [path]
     */
    fun get(path: String, callback: (request: Request, response: Response) -> Unit) {
        app.get(path) {
            req, res -> callback.invoke(Request(req), Response(res))
        }
    }

    /**
     * Starts listening on UNIX socket with [socketPath]
     */
    fun listen(socketPath: String, callback: (() -> Unit)?) {
        app.listen(socketPath) {
            callback?.invoke()
        }
    }

    /**
     * Starts listening on [port]
     */
    fun listen(port: Int, callback: (() -> Unit)?) {
        app.listen(port) {
            callback?.invoke()
        }
    }

    /**
     * Starts listening on [host]:[port]
     */
    fun listen(port: Int, host: String, callback: (() -> Unit)?) {
        app.listen(port, host) {
            callback?.invoke()
        }
    }

}