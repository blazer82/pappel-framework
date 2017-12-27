package pappel

import pappel.http.Request
import pappel.http.Response

/**
 * Router base class
 *
 * A base class for all routers including the [Application] which also acts as a router.
 * All user defined routers may extend this class.
 *
 * @constructor Creates a new router.
 */
abstract class Router {

    /**
     * References the expressjs node module.
     */
    protected val express: dynamic = require("express")

    /**
     * References the expressjs router.
     */
    open val expressRouter: dynamic = express.Router()

    /**
     * References the application the router is attached to.
     */
    open val application: Application
        get() = _application!! // Supposed to be registered in time

    private var _application: Application? = null

    /**
     * Handles all requests for [path].
     * @param path Path relative to the router's base path
     * @param callback Callback to handle requests
     */
    fun all(path: String, callback: (request: Request, response: Response) -> Unit) {
        expressRouter.all(path) {
            req, res -> callback.invoke(Request(req), Response(res))
        }
    }

    /**
     * Handles DELETE requests for [path].
     * @param path Path relative to the router's base path
     * @param callback Callback to handle requests
     */
    fun delete(path: String, callback: (request: Request, response: Response) -> Unit) {
        expressRouter.delete(path) {
            req, res -> callback.invoke(Request(req), Response(res))
        }
    }

    /**
     * Handles GET requests for [path].
     * @param path Path relative to the router's base path
     * @param callback Callback to handle requests
     */
    fun get(path: String, callback: (request: Request, response: Response) -> Unit) {
        expressRouter.get(path) {
            req, res -> callback.invoke(Request(req), Response(res))
        }
    }

    /**
     * Registers a global request [callback].
     * @param callback Callback to handle requests
     */
    fun onRequest(callback: (request: Request, response: Response, next: () -> Unit) -> Unit) {
        expressRouter.use {
            req, res, n -> callback.invoke(Request(req), Response(res), n as () -> Unit)
        }
    }

    /**
     * Handles POST requests for [path].
     * @param path Path relative to the router's base path
     * @param callback Callback to handle requests
     */
    fun post(path: String, callback: (request: Request, response: Response) -> Unit) {
        expressRouter.post(path) {
            req, res -> callback.invoke(Request(req), Response(res))
        }
    }

    /**
     * Handles PUT requests for [path].
     * @param path Path relative to the router's base path
     * @param callback Callback to handle requests
     */
    fun put(path: String, callback: (request: Request, response: Response) -> Unit) {
        expressRouter.put(path) {
            req, res -> callback.invoke(Request(req), Response(res))
        }
    }

    /**
     * Uses [router] for [path].
     * @param path Path relative to the router's base path
     * @param router Instance of another router to use for [path]
     */
    fun use(path: String, router: Router) {
        expressRouter.use(path, router.expressRouter)
        router.registerParent(this)
    }

    /**
     * Register parent [router] or application
     */
    private fun registerParent(router: Router) {
        if (router is Application) {
            _application = router
        }
        else {
            _application = router.application
        }
    }

}