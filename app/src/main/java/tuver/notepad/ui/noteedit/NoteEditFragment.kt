package tuver.notepad.ui.noteedit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import tuver.notepad.R
import tuver.notepad.databinding.FragmentNoteEditBinding
import tuver.notepad.util.extension.viewModelsFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NoteEditFragment : Fragment(R.layout.fragment_note_edit) {

    @Inject
    lateinit var viewModelFactory: NoteEditViewModel.Companion.Factory

    private val fragmentArgs: NoteEditFragmentArgs by navArgs()

    private val viewModel: NoteEditViewModel by viewModelsFactory { viewModelFactory.create(fragmentArgs.noteSummaryModel) }

    private var bindingFiled: FragmentNoteEditBinding? = null

    private val binding: FragmentNoteEditBinding
        get() = bindingFiled ?: throw IllegalAccessException("Cannot access binding!")

    private fun onNavigationChanged(navigation: NoteEditNavigation) {
        when (navigation) {
            is NoteEditNavigation.NavigateUp -> findNavController().navigateUp()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)?.also {
            bindingFiled = FragmentNoteEditBinding.bind(it)
            binding.viewmodel = viewModel
            binding.lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navigation.observe(viewLifecycleOwner, this::onNavigationChanged)
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.onBackPress()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        bindingFiled = null
    }

}