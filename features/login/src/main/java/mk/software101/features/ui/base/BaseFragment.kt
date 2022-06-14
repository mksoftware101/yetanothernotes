package mk.software101.features.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VIEW_BINDING : ViewBinding,
        VIEW_MODEL : BaseViewModel<VIEW_PARTIAL_STATE, VIEW_STATE>,
        VIEW_PARTIAL_STATE : BasePartialState,
        VIEW_STATE : BaseState> : Fragment() {

    private var _binding: VIEW_BINDING? = null
    protected val binding get() = _binding!!

    protected val viewModel by lazy { initViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = getViewBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner) { state -> render(state) }
        viewModel.initialize()
        setupUI()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): VIEW_BINDING
    abstract fun initViewModel(): VIEW_MODEL
    abstract fun setupUI()
    abstract fun render(viewState: VIEW_STATE)
}