package tuver.notepad.ui.notesummarylist

import androidx.paging.PagingData
import tuver.notepad.model.NoteSummaryModel
import kotlinx.coroutines.flow.Flow

interface NoteSummaryListState {

    val noteListPagingData: Flow<PagingData<NoteSummaryModel>>

    class Mutable(
        override val noteListPagingData: Flow<PagingData<NoteSummaryModel>>
    ) : NoteSummaryListState

}