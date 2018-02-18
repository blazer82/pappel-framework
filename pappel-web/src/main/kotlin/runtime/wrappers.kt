package runtime.wrappers

fun Any.getOwnPropertyNames(): Array<String> {
    @Suppress("UNUSED_VARIABLE")
    val me = this
    return js("Object.getOwnPropertyNames(me)")
}

fun toPlainObjectStripNull(me: Any): dynamic {
    val obj = js("({})")
    for (p in me.getOwnPropertyNames().filterNot { it == "__proto__" || it == "constructor" }) {
        js("if (me[p] != null) { obj[p]=me[p] }")
    }
    return obj
}
