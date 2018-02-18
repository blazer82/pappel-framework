package demo

import kotlinx.html.*
import kotlinx.html.js.onClickFunction
import pappel.Component
import react.ReactComponentSpec
import react.dom.ReactDOMBuilder

class CounterComponent : Component<Component.Props, CounterComponent.State>() {

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

    companion object : ReactComponentSpec<CounterComponent, Component.Props, CounterComponent.State>

    class State(var counter: Int = 0) : Component.State()
}