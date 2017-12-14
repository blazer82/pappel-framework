package framework.express

import kotlin.js.Json
import kotlin.js.json

class Response(external private val res: dynamic) {

    /**
     * Set content [type]
     */
    fun setContentType(type: String) {
        res.type(type)
    }

    /**
     * Send response [string]
     */
    fun send(string: String) {
        res.send(string)
    }

    /**
     * Send [data] response as JSON
     * @todo Maybe find a more useful type than Json for [data]
     */
    fun sendJSON(data: Json) {
        res.json(data)
    }

    /**
     * Render [view] and send response to client
     */
    fun render(view: String) {
        res.render("$view.html")
    }

    /**
     * Render [view] and send response to [callback]
     */
    fun render(view: String, callback: (String?) -> Unit) {
        res.render("$view.html") {
            _, html -> callback.invoke(html as? String)
        }
    }

    /**
     * Render [view] using [parameters] and send response to client
     * @todo Find a more useful type than Json for [parameters]
     */
    fun render(view: String, parameters: Json) {
        res.render("$view.html", parameters)
    }

    /**
     * Render [view] using [parameters] and send response to [callback]
     * @todo Find a more useful type than Json for [parameters]
     */
    fun render(view: String, parameters: Json, callback: (String?) -> Unit) {
        res.render("$view.html", parameters) {
            _, html -> callback.invoke(html as? String)
        }
    }

}