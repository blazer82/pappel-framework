package pappel

import react.RProps
import react.RState
import react.dom.ReactDOMComponent

abstract class Component<P : Component.Props, S : Component.State> : ReactDOMComponent<P, S>() {

    open class Props : RProps()
    open class State : RState
}
