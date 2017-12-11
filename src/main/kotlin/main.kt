import kotlin.js.json

external fun require(module:String):dynamic

fun main(args: Array<String>) {
    println("Node.js service built with Kotlin")

    val express = require("express")
    val app = express()

    app.get("/") { _, res ->
        res.type("text/plain")
        res.send("Hello world")
    }

    app.get("/html") {
        _, response ->
        response.type("text/html")
        response.send("<html><head></head><body><h1>HTML Response Test</h1></body></html>")
    }

    app.get("/json") {
        _, response ->
        response.json(json(
                Pair("foo", "bar"),
                Pair("flag", true),
                Pair("list", arrayOf(1, 2, 3))
        ))
    }

    app.listen(3000, {
        println("Listening on port 3000")
    })
}