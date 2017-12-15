import pappel.Application
import pappel.http.Status

external fun require(module:String):dynamic

fun main(args: Array<String>) {
    println("Node.js service built with Kotlin")

    val express = require("express")
    val application = Application(express())

    application.get("/") { _, response ->
        response.setContentType("text/plain")
        response.send("Hello world")
    }

    application.get("/html") {
        _, response ->
        response.setContentType("text/html")
        response.send("<html><head></head><body><h1>HTML Response Test</h1></body></html>")
    }

    application.get("/json") {
        _, response ->
        response.sendJSON(arrayOf(1, 2, "test", mapOf("foo" to "bar")) as Array<Any?>)
    }

    application.get("/view") {
        _, response ->
        response.render("test", mapOf(
                "text" to "This text is dynamic",
                "int" to 7,
                "complex" to mapOf("foo" to "bar")
        ))
    }

    application.get("/error") {
        _, response ->
        response.setStatus(Status.INTERNAL_SERVER_ERROR)
        response.end()
    }

    application.get("/header") {
        _, response ->
        response.setHeader("foo", "bar")
        response.setHeaders(mapOf("a" to "b", "c" to "d"))
        response.end()
    }

    application.get("/request/:id") {
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

    application.listen(3000, {
        println("Listening on port 3000")
    })
}