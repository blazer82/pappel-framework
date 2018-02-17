import kotlinx.html.*
import kotlinx.html.js.*
import kotlin.browser.*

fun main(args: Array<String>) {
    println("Hello?")

    val element = document.getElementById("dyn-text")
    println(element)

    element?.innerHTML = "dyn text"
}