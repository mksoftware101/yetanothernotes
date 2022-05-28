package mk.software101.features.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VMI : ViewIntent, VMA : ViewModelAction, VS : ViewState, VB : ViewBinding,
        VM : BaseViewModel<VMI, VMA, VS>> : Fragment() {

    private var _binding: VB? = null

    // This property is only valid between onCreateView and onDestroyView
    protected val viewBinding get() = _binding!!

    val viewModel: VM by lazy { createViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getScreenViewBinding(inflater, container)
        setupUI()
        setupEvents()
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(viewLifecycleOwner) { viewState -> render(viewState) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected abstract fun setupUI()
    protected abstract fun setupEvents()
    protected abstract fun getScreenViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB
    protected abstract fun createViewModel(): VM
    protected abstract fun render(viewState: VS)
}