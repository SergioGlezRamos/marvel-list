package com.marvellist.domain.utils

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope

suspend fun CoroutineScope.tryCatch(
    tryBlock: suspend CoroutineScope.() -> Unit,
    catchBlock: suspend CoroutineScope.(Throwable) -> Unit,
    handleCancellationExceptionManually: Boolean = false) {
    try {
        tryBlock()
    } catch (e: Throwable) {
        if (e !is CancellationException || handleCancellationExceptionManually) {
            catchBlock(e)
        } else {
            throw e
        }
    }
}

suspend fun CoroutineScope.tryCatchFinally(
    tryBlock: suspend CoroutineScope.() -> Unit,
    catchBlock: suspend CoroutineScope.(Throwable) -> Unit,
    finallyBlock: suspend CoroutineScope.() -> Unit,
    handleCancellationExceptionManually: Boolean = false) {

    var caughtThrowable: Throwable? = null

    try {
        tryBlock()
    } catch (e: Throwable) {
        if (e !is CancellationException || handleCancellationExceptionManually) {
            catchBlock(e)
        } else {
            caughtThrowable = e
        }
    } finally {
        if (caughtThrowable is CancellationException && !handleCancellationExceptionManually) {
            throw caughtThrowable
        } else {
            finallyBlock()
        }
    }
}

/**
 * Filters CancellationException for us and makes
 * sure it is propagated unless we explicitly state that we want to handle it manually.
 * @param tryBlock Function to try t execute
 * @param finallyBlock Function to make in finally execution
 * @param handleCancellationExceptionManually Function to handle Cancellation Exception in coroutine
 */
suspend fun CoroutineScope.tryFinally(
    tryBlock: suspend CoroutineScope.() -> Unit,
    finallyBlock: suspend CoroutineScope.() -> Unit,
    suppressCancellationException: Boolean = false) {

    var caughtThrowable: Throwable? = null

    try {
        tryBlock()
    } catch (e: CancellationException) {
        if (!suppressCancellationException) {
            caughtThrowable = e
        }
    } finally {
        if (caughtThrowable is CancellationException && !suppressCancellationException) {
            throw caughtThrowable
        } else {
            finallyBlock()
        }
    }
}
