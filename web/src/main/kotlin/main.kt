import demo.Application
import react.dom.ReactDOM
import react.dom.render
import kotlin.browser.document

fun main(args: Array<String>) {

    ReactDOM.render(document.getElementById("application")) {
        Application {}
    }

}
