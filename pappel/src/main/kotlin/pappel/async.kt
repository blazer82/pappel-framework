import kotlin.coroutines.experimental.*
import kotlin.js.Promise

fun async(block: suspend () -> Unit) {
    block.startCoroutine(StandaloneCoroutine(EmptyCoroutineContext))
}

suspend fun <T>await(block: () -> Promise<T>) = suspendCoroutine<T> {
    continuation ->
    block().then {
        value -> continuation.resume(value)
    }.catch {
        error -> continuation.resumeWithException(error)
    }
}

private class StandaloneCoroutine(override val context: CoroutineContext): Continuation<Unit> {
    override fun resume(value: Unit) {}

    override fun resumeWithException(error: Throwable) {}
}