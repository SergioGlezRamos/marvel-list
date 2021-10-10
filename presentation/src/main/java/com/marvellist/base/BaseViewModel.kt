package com.marvellist.base

import androidx.lifecycle.ViewModel
import com.marvellist.domain.utils.tryCatch
import com.marvellist.manager.ConcurrencyManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BaseViewModel : ViewModel() {

    var concurrencyManager: ConcurrencyManager = ConcurrencyManager()

    protected fun executeUseCase(fullException: Boolean = false, block: suspend CoroutineScope.() -> Unit): Job {
        return concurrencyManager.launch(fullException = fullException, block = block, dispatcher = Dispatchers.Main)
    }

    protected fun executeUseCaseWithException(block: suspend CoroutineScope.() -> Unit,
                                              exceptionBlock: suspend CoroutineScope.(Throwable) -> Unit,
                                              handleCancellationManually: Boolean = false,
                                              fullException: Boolean = false): Job {
        return concurrencyManager.launch(fullException = fullException,dispatcher = Dispatchers.Main) {
            tryCatch(block, exceptionBlock, handleCancellationManually)
        }
    }

}