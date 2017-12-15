package demo

import pappel.Router
import pappel.http.Status

class Router : Router() {

    /**
     * Initialize demo routes
     */
    init {

        /**
         * Render Hello World
         */
        get("/") {
            _, response ->
            response.setContentType("text/plain")
            response.send("Hello world")
        }

        /**
         * Render JSON output
         */
        get("/json") {
            _, response ->
            response.sendJSON(
                    arrayOf(1, 2, "test", mapOf("foo" to "bar")) as Array<Any?>
            )
        }

        /**
         * Render request attributes using request view
         */
        get("/request") {
            request, response ->
            response.render("request", mapOf(
                    "baseURL" to request.baseURL,
                    "body" to request.body,
                    "cookies" to request.cookies?.toString(),
                    "hostname" to request.hostname,
                    "ip" to request.ip,
                    "ips" to request.ips?.toString(),
                    "method" to request.method,
                    "parameters" to request.parameters?.toString(),
                    "path" to request.path,
                    "protocol" to request.protocol,
                    "query" to request.query?.toString()
            ) as Map<String, Any>)
        }

        get("/param/:id") {
            request, response ->
            response.send("Param id was ${request.parameters!!["id"]}")
        }

        /**
         * Return internal server error
         */
        get("/error") {
            _, response ->
            response.setStatus(Status.INTERNAL_SERVER_ERROR)
            response.end()
        }

        /**
         * Set response headers
         */
        get("/header") {
            _, response ->
            response.setHeader("foo", "bar")
            response.setHeaders(mapOf("a" to "b", "c" to "d"))
            response.end()
        }
    }

}