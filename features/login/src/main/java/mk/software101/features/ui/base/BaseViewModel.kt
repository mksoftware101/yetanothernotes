package mk.software101.features.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<VIEW_PARTIAL_STATE : BasePartialState, VIEW_STATE : BaseState> :
    ViewModel() {

    protected val _state = MutableLiveData<VIEW_STATE>()
    val state: LiveData<VIEW_STATE> = _state

    abstract fun initialize()
    abstract fun reduce(partialState: VIEW_PARTIAL_STATE)
}