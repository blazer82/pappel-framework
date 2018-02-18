package demo

import kotlinx.html.*
import kotlinx.html.js.onClickFunction
import react.RState
import react.ReactComponentNoProps
import react.ReactComponentSpec
import react.dom.ReactDOMBuilder
import react.dom.ReactDOMComponent

class CounterComponent : ReactDOMComponent<ReactComponentNoProps, CounterComponent.State>() {

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

    companion object : ReactComponentSpec<CounterComponent, ReactComponentNoProps, State>

    class State(var counter: Int = 0) : RState
}