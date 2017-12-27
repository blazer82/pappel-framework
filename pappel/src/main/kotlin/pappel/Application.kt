package pappel

import kotlin.js.Promise

external fun require(module:String):dynamic

/**
 * Application base class
 *
 * Represents an instance of an application and implements [Router].
 *
 * @constructor Creates a new application.
 */
open class Application : Router() {

    private val app: dynamic = express()

    /**
     * References the expressjs application.
     */
    override val expressRouter: dynamic = app

    init {
        val mustacheExpress = require("mustache-express")

        app.engine("html", mustacheExpress())
        app.set("view engine", "html")
    }

    /**
     * Starts listening on UNIX socket with [socketPath].
     * @param socketPath Socket path on the filesystem.
     * @return Promise<Unit>
     */
    fun listen(socketPath: String): Promise<Unit> =
            Promise {
                resolve, _ ->
                app.listen(socketPath) {
                    resolve.invoke(Unit)
                }
            } // TODO: Add error handling

    /**
     * Starts listening on [port].
     * @param port TCP port to listen on.
     * @return Promise<Unit>
     */
    fun listen(port: Int): Promise<Unit> =
            Promise {
                resolve, _ ->
                app.listen(port) {
                    resolve.invoke(Unit)
                }
            } // TODO: Add error handling

    /**
     * Starts listening on [host]:[port].
     * @param port TCP port to listen on.
     * @param host Hostname to bind on.
     * @return Promise<Unit>
     */
    fun listen(port: Int, host: String): Promise<Unit> =
            Promise {
                resolve, _ ->
                app.listen(port, host) {
                    resolve.invoke(Unit)
                }
            } // TODO: Add error handling

}