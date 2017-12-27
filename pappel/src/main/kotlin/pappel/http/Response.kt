package pappel.http

import pappel.JSONUtils

class Response(external private val res: dynamic) {

    /**
     * End response
     * May be called instead of [Response.send]
     */
    fun end() {
        res.end()
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

    /**
     * Send response [string]
     */
    fun send(string: String) {
        res.send(string)
    }

    /**
     * Send [data] response as JSON
     */
    fun sendJSON(data: Map<String, Any?>) {
        res.json(JSONUtils.toJSON(data))
    }

    /**
     * Send [data] response as JSON
     */
    fun sendJSON(data: Iterable<Any?>) {
        res.json(JSONUtils.toJSON(data))
    }

    /**
     * Set content [type]
     */
    fun setContentType(type: String) {
        res.type(type)
    }

    /**
     * Set header [field] to [value]
     */
    fun setHeader(field: String, value: String) {
        res.append(field, value)
    }

    /**
     * Set multiple headers
     */
    fun setHeaders(fields: Map<String, String>) {
        fields.forEach {
            entry -> res.append(entry.key, entry.value)
        }
    }

    /**
     * Set [status]
     */
    fun setStatus(status: Status) {
        res.status(status.code)
    }

}