package mk.software101.features.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<VMI : ViewIntent, VMA : ViewModelAction, VS : ViewState> :
    ViewModel() {

    protected val _viewState = MutableLiveData<VS>()
    val viewState: LiveData<VS> = _viewState

    protected abstract fun mapIntentToAction(intent: VMI): VMA

    protected abstract fun handleAction(action: VMA)

    fun dispatchIntent(intent: VMI) {
        handleAction(action = mapIntentToAction(intent))
    }
}