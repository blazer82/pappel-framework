package framework.express

class Response(external private val res: dynamic) {

    fun send(string: String) {
        res.send(string)
    }

    fun type(type: String) {
        res.type(type)
    }

    fun json(data: Any) {
        res.json(data)
    }

}