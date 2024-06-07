package com.caresaas.caresaasmobiletask.core.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<S>(initialState: S) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    protected fun <T> tryToExecute(
        function: suspend () -> T,
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit,
        inScope: CoroutineScope = viewModelScope,
    ): Job {
        return inScope.launch {
            try {
                val result = function()
                onSuccess(result)
            } catch (t: Throwable) {
                onError(t)
            }
        }
    }

    protected fun <T> tryToCollect(
        function: suspend () -> Flow<T>,
        onNewValue: (T) -> Unit,
        onError: (Throwable) -> Unit,
        inScope: CoroutineScope = viewModelScope,
    ): Job {
        return inScope.launch {
            function()
                .catch { onError(it) }
                .distinctUntilChanged()
                .collectLatest { onNewValue(it) }
        }
    }

    protected fun updateState(updater: (S) -> S) {
        _state.update(updater)
    }
}