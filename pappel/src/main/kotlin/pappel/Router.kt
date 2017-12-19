package pappel

import pappel.http.Request
import pappel.http.Response

abstract class Router {

    protected val express: dynamic = require("express")
    open val expressRouter: dynamic = express.Router()

    open val application: Application
        get() = _application!! // Supposed to be registered in time

    private var _application: Application? = null

    /**
     * Handle all requests for [path]
     */
    fun all(path: String, callback: (request: Request, response: Response) -> Unit) {
        expressRouter.all(path) {
            req, res -> callback.invoke(Request(req), Response(res))
        }
    }

    /**
     * Handle DELETE request for [path]
     */
    fun delete(path: String, callback: (request: Request, response: Response) -> Unit) {
        expressRouter.delete(path) {
            req, res -> callback.invoke(Request(req), Response(res))
        }
    }

    /**
     * Handle GET request for [path]
     */
    fun get(path: String, callback: (request: Request, response: Response) -> Unit) {
        expressRouter.get(path) {
            req, res -> callback.invoke(Request(req), Response(res))
        }
    }

    /**
     * Register global request [callback]
     */
    fun onRequest(callback: (request: Request, response: Response, next: () -> Unit) -> Unit) {
        expressRouter.use {
            req, res, n -> callback.invoke(Request(req), Response(res), n as () -> Unit)
        }
    }

    /**
     * Handle POST request for [path]
     */
    fun post(path: String, callback: (request: Request, response: Response) -> Unit) {
        expressRouter.post(path) {
            req, res -> callback.invoke(Request(req), Response(res))
        }
    }

    /**
     * Handle PUT request for [path]
     */
    fun put(path: String, callback: (request: Request, response: Response) -> Unit) {
        expressRouter.put(path) {
            req, res -> callback.invoke(Request(req), Response(res))
        }
    }

    /**
     * Use [router] for [path]
     */
    fun use(path: String, router: Router) {
        expressRouter.use(path, router.expressRouter)
        router.registerParent(this)
    }

    /**
     * Register parent [router] or application
     */
    fun registerParent(router: Router) {
        if (router is Application) {
            _application = router
        }
        else {
            _application = router.application
        }
    }

}