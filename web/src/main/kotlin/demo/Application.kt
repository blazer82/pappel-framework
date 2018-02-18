package demo

import react.ReactComponentNoProps
import react.ReactComponentNoState
import react.ReactComponentSpec
import react.dom.ReactDOMBuilder
import react.dom.ReactDOMComponent

class Application : ReactDOMComponent<ReactComponentNoProps, ReactComponentNoState>() {

    init {
        state = ReactComponentNoState()
    }

    override fun ReactDOMBuilder.render() {
        CounterComponent {}
    }

    companion object : ReactComponentSpec<Application, ReactComponentNoProps, ReactComponentNoState>
}