# Pappel Framework

***A Kotlin framework for Node.js applications***

## Features

* Clean and well-**typed** API
* Full **MVC support**
* Powered by popular node modules (**express**, **sequelize**, …)
* **async/await** language support
* **Mustache** template engine
* Built-in **React** support
* Scalable complexity including stackable applications

## Example code

### Routing

```kotlin
import pappel.Router

class Router : Router() {

    /**
     * Initialize routes
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

### Database and async/await support

```kotlin
async {
    await { db.connect() }

    await { model.user.sync(true) }

    val user = model.user.new()
    user1.username = "demo"

    await { model.user.save(user) }
}
```

### Templates

```html
<html>
    <head></head>
    <body>
        <p>Request on path {{path}}</p>
    </body>
</html>
```

### React components

```kotlin
class CounterComponent : ReactDOMComponent<ReactComponentNoProps, CounterComponent.State>() {

    init {
        state = State(counter = 0)
    }

    override fun ReactDOMBuilder.render() {
        div("counter") {
            p {
                +"Counter: ${state.counter}"
            }
            button {
                +"Increase"

                onClickFunction = {
                    setState {
                        counter = state.counter + 1
                    }
                }
            }
        }
    }

    companion object : ReactComponentSpec<CounterComponent, ReactComponentNoProps, State>

    class State(var counter: Int = 0) : RState
}
```

## Disclaimer

Pappel framework is still under development and **not yet ready to use** as many features are still missing and the API may significantly change in the future.

If you're interested in **updates** or would like to **support** Pappel framework in any way, do not hesitate to get in touch.
