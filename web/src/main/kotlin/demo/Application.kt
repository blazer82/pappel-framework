package demo

import pappel.Application
import pappel.Component
import react.ReactComponentSpec
import react.dom.ReactDOMBuilder

class Application : Application<Component.Props, Component.State>() {

    init {
        state = Component.State()
    }

    override fun ReactDOMBuilder.render() {
        CounterComponent {}
    }

    companion object : ReactComponentSpec<demo.Application, Component.Props, Component.State>
}