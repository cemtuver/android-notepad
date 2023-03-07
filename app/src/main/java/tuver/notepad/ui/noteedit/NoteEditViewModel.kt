package tuver.notepad.ui.noteedit

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import tuver.notepad.domain.NoteDeleteUseCase
import tuver.notepad.domain.NoteGetUseCase
import tuver.notepad.domain.NoteSaveUseCase
import tuver.notepad.model.NoteModel
import tuver.notepad.model.NoteSummaryModel
import tuver.notepad.util.SingleLiveEvent
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class NoteEditViewModel @AssistedInject constructor(
    @Assisted noteSummaryModel: NoteSummaryModel?,
    private val noteGetUseCase: NoteGetUseCase,
    private val noteSaveUseCase: NoteSaveUseCase,
    private val noteDeleteUseCase: NoteDeleteUseCase
) : ViewModel() {

    private lateinit var noteModel: NoteModel

    private val mutableNavigation = tuver.notepad.util.SingleLiveEvent<NoteEditNavigation>()

    private val mutableState = NoteEditState.Mutable(noteSummaryModel)

    val navigation: LiveData<NoteEditNavigation>
        get() = mutableNavigation

    val state: NoteEditState
        get() = mutableState

    init {
        when (noteSummaryModel) {
            null -> noteModel = NoteModel()
            else -> getNoteModel(noteSummaryModel.id)
        }
    }

    private fun getNoteModel(noteId: String) {
        viewModelScope.launch {
            noteGetUseCase.getNote(noteId)
                .onSuccess {
                    noteModel = it
                    mutableState.description.postValue(it.description)
                }
        }
    }

    fun onBackPress() {
        if (!::noteModel.isInitialized) {
            mutableNavigation.postValue(NoteEditNavigation.NavigateUp)
            return
        }

        viewModelScope.launch {
            val editedNoteModel = mutableState.run {
                noteModel.copy(
                    title = title.value ?: "",
                    imageUrl = imageUrl.value?.takeIf { it.isNotBlank() },
                    description = description.value ?: ""
                )
            }

            if (noteModel != editedNoteModel) {
                noteSaveUseCase.saveNote(editedNoteModel)
            }

            mutableNavigation.postValue(NoteEditNavigation.NavigateUp)
        }
    }

    fun onDeleteNotClick() {
        if (!::noteModel.isInitialized || noteModel.isNew) {
            mutableNavigation.postValue(NoteEditNavigation.NavigateUp)
            return
        }

        viewModelScope.launch {
            noteDeleteUseCase.deleteNote(noteModel)
            mutableNavigation.postValue(NoteEditNavigation.NavigateUp)
        }
    }

    companion object {

        @AssistedFactory
        interface Factory {
            fun create(noteSummaryModel: NoteSummaryModel?): NoteEditViewModel
        }

    }

}