import kotlin.coroutines.experimental.*
import kotlin.js.Promise

/**
 * Async functionality for async/await ability.
 *
 * Starts an asynchronous execution [block] usually used in an async/await construct.
 */
fun async(block: suspend () -> Unit) {
    block.startCoroutine(StandaloneCoroutine(EmptyCoroutineContext))
}

/**
 * Await functionality for async/await ability.
 *
 * Suspends current asychronous execution block and awaits the resolution of a Promise<[T]>.
 * Must be used within an [async] block.
 */
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