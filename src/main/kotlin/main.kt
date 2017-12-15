import pappel.Application

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
        response.sendJSON(arrayOf(1, 2, "test", mapOf("foo" to "bar")))
    }

    application.get("/view") {
        _, response ->
        response.render("test", mapOf(
                "text" to "This text is dynamic",
                "int" to 7,
                "complex" to mapOf("foo" to "bar")
        ))
    }

    application.listen(3000, {
        println("Listening on port 3000")
    })
}