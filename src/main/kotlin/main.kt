import framework.express.Application
import kotlin.js.json

external fun require(module:String):dynamic

fun main(args: Array<String>) {
    println("Node.js service built with Kotlin")

    val express = require("express")
    val application = Application(express())

    application.get("/") { _, response ->
        response.type("text/plain")
        response.send("Hello world")
    }

    application.get("/html") {
        _, response ->
        response.type("text/html")
        response.send("<html><head></head><body><h1>HTML Response Test</h1></body></html>")
    }

    application.get("/json") {
        _, response ->
        response.json(json(
                Pair("foo", "bar"),
                Pair("flag", true),
                Pair("list", arrayOf(1, 2, 3))
        ))
    }

    application.listen(3000, {
        println("Listening on port 3000")
    })
}