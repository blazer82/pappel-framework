package framework.express

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
     */
    fun sendJSON(data: Map<String, Any>) {
        res.json(JSONUtils.toJSON(data))
    }

    /**
     * Send [data] response as JSON
     */
    fun sendJSON(data: Array<Any>) {
        res.json(JSONUtils.toJSON(data))
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
     */
    fun render(view: String, parameters: Map<String, Any>) {
        res.render("$view.html", JSONUtils.toJSON(parameters))
    }

    /**
     * Render [view] using [parameters] and send response to [callback]
     */
    fun render(view: String, parameters: Map<String, Any>, callback: (String?) -> Unit) {
        res.render("$view.html", JSONUtils.toJSON(parameters)) {
            _, html -> callback.invoke(html as? String)
        }
    }

}