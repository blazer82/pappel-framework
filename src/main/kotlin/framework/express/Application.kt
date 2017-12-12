package framework.express

class Application(external private val app: dynamic) {

    fun get(path: String, callback: (request: Request, response: Response) -> Unit) {
        app.get(path) {
            req, res -> callback.invoke(Request(req), Response(res))
        }
    }

    fun listen(port: Int, callback: () -> Unit) {
        app.listen(port) {
            callback.invoke()
        }
    }

}