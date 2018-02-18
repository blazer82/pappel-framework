import kotlinx.html.*
import kotlinx.html.js.*
import react.*
import react.dom.*
import kotlin.browser.*

fun main(args: Array<String>) {
    println("Hello?")

    val element = document.getElementById("dyn-text")
    println(element)

    element?.innerHTML = "dyn text"

    ReactDOM.render(document.getElementById("react-test")) {
        Application {}
    }

}

class Application : ReactDOMComponent<ReactComponentNoProps, ApplicationPageState>() {

    init {
        state = ApplicationPageState()
    }

    override fun ReactDOMBuilder.render() {
        p {
            +"react test"
        }
    }

    companion object : ReactComponentSpec<Application, ReactComponentNoProps, ApplicationPageState>

}

class ApplicationPageState : RState