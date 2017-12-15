# Pappel Framework

***A Kotlin framework for Node.js applications***

## Example code

`main.kt`

```kotlin
import pappel.Application

fun main(args: Array<String>) {

    /**
     * Initialize application
     */
    val application = Application()

    /**
     * Register router
     */
    application.use("/", Router())

    /**
     * Listen on port 3000
     */
    application.listen(3000)
}
``` 

`Router.kt`

```kotlin
import pappel.Router

class Router : Router() {

    /**
     * Initialize demo routes
     */
    init {

        /**
         * Render Hello World on /
         */
        get("/") {
            _, response ->
            response.send("Hello world")
        }

        /**
         * Render JSON output on /json
         */
        get("/json") {
            _, response ->
            response.sendJSON(
                    arrayOf(1, 2, "test", mapOf("foo" to "bar")) as Array<Any?>
            )
        }

        /**
         * Render view using parameters on /view
         */
        get("/view") {
            request, response ->
            response.render("view", mapOf("path" to request.path) as Map<String, Any>)
        }

        /**
        * Handle request parameter 
        */
        get("/param/:id") {
            request, response ->
            response.send("Parameter id was ${request.parameters!!["id"]}")
        }
    }
}
``` 

`view.html`

```html
<html>
    <head></head>
    <body>
        <p>Request on path {{path}}</p>
    </body>
</html>
```

