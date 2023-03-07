package tuver.notepad.domain

import androidx.paging.PagingData
import tuver.notepad.model.NoteSummaryModel
import kotlinx.coroutines.flow.Flow

interface NoteGetNoteSummaryListPagingDataUseCase {

    fun getNoteSummaryListPagingData(
        pageSize: Int,
        shortDescriptionCharLimit: Int
    ): Flow<PagingData<NoteSummaryModel>>

}