package framework.express

class Response(external private val res: dynamic) {

    /**
     * Sends String response
     */
    fun send(string: String) {
        res.send(string)
    }

    /**
     * Sets response [type]
     */
    fun type(type: String) {
        res.type(type)
    }

    /**
     * Sends JSON response
     */
    fun json(data: Any) {
        res.json(data)
    }

}