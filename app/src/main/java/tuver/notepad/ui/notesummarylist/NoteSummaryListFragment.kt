package tuver.notepad.ui.notesummarylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import tuver.notepad.R
import tuver.notepad.databinding.FragmentNoteSummaryListBinding
import tuver.notepad.model.NoteSummaryModel
import tuver.notepad.ui.notesummarylist.adapter.NoteSummaryListAdapter
import tuver.notepad.util.extension.viewModelsFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NoteSummaryListFragment : Fragment(R.layout.fragment_note_summary_list) {

    @Inject
    lateinit var noteSummaryListAdapterFactory: NoteSummaryListAdapter.Companion.Factory

    @Inject
    lateinit var viewModelFactory: NoteSummaryListViewModel.Companion.Factory

    private val noteListAdapter by lazy { noteSummaryListAdapterFactory.create { viewModel.onNoteSummaryClick(it) } }

    private val viewModel: NoteSummaryListViewModel by viewModelsFactory {
        viewModelFactory.create(
            resources.getInteger(R.integer.note_list_page_size),
            resources.getInteger(R.integer.note_list_short_description_char_limit)
        )
    }

    private var bindingFiled: FragmentNoteSummaryListBinding? = null

    private val binding: FragmentNoteSummaryListBinding
        get() = bindingFiled ?: throw IllegalAccessException("Cannot access binding!")

    private fun setupRecyclerView() {
        binding.notesRecycler.apply {
            adapter = noteListAdapter
            layoutManager = StaggeredGridLayoutManager(
                resources.getInteger(R.integer.note_list_grid_span_count),
                StaggeredGridLayoutManager.VERTICAL
            )
        }
    }

    private fun onNavigationChanged(navigation: NoteSummaryListNavigation) {
        when (navigation) {
            is NoteSummaryListNavigation.NavigateToNoteCreate -> navigateToNoteEditFragment(null)
            is NoteSummaryListNavigation.NavigateToNoteEdit -> navigateToNoteEditFragment(navigation.noteSummaryModel)
        }
    }

    private fun navigateToNoteEditFragment(noteSummaryModel: NoteSummaryModel?) {
        val action = NoteSummaryListFragmentDirections.toNoteEditFragment(
            noteSummaryModel
        )

        findNavController().navigate(action)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)?.also {
            bindingFiled = FragmentNoteSummaryListBinding.bind(it)
            binding.viewmodel = viewModel
            binding.lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        viewModel.navigation.observe(viewLifecycleOwner, this::onNavigationChanged)
        lifecycleScope.launch {
            viewModel.state.noteListPagingData.collectLatest { pagingData ->
                noteListAdapter.submitData(pagingData)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bindingFiled = null
    }

}