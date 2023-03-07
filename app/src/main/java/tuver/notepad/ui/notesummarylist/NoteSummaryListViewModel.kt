package tuver.notepad.ui.notesummarylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import tuver.notepad.domain.NoteGetNoteSummaryListPagingDataUseCase
import tuver.notepad.model.NoteSummaryModel
import tuver.notepad.util.SingleLiveEvent
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class NoteSummaryListViewModel @AssistedInject constructor(
    @Assisted("pageSize") pageSize: Int,
    @Assisted("shortDescriptionCharLimit") shortDescriptionCharLimit: Int,
    noteGetNoteSummaryListPagingDataUseCase: NoteGetNoteSummaryListPagingDataUseCase
) : ViewModel() {

    private val mutableNavigation = tuver.notepad.util.SingleLiveEvent<NoteSummaryListNavigation>()

    private val mutableState by lazy {
        NoteSummaryListState.Mutable(
            noteGetNoteSummaryListPagingDataUseCase
                .getNoteSummaryListPagingData(pageSize, shortDescriptionCharLimit)
                .cachedIn(viewModelScope)
        )
    }

    val navigation: LiveData<NoteSummaryListNavigation>
        get() = mutableNavigation

    val state: NoteSummaryListState
        get() = mutableState

    fun onCreateNoteButtonClick() {
        mutableNavigation.postValue(NoteSummaryListNavigation.NavigateToNoteCreate)
    }

    fun onNoteSummaryClick(noteSummaryModel: NoteSummaryModel) {
        mutableNavigation.postValue(NoteSummaryListNavigation.NavigateToNoteEdit(noteSummaryModel))
    }

    companion object {

        @AssistedFactory
        interface Factory {
            fun create(
                @Assisted("pageSize") pageSize: Int,
                @Assisted("shortDescriptionCharLimit") shortDescriptionCharLimit: Int
            ): NoteSummaryListViewModel
        }

    }

}