package tuver.notepad.data

import androidx.paging.PagingData
import tuver.notepad.model.NoteSummaryModel
import kotlinx.coroutines.flow.Flow

interface NoteSummaryRepository {

    fun getNoteSummaryListPagingData(
        pageSize: Int,
        shortDescriptionCharLimit: Int
    ): Flow<PagingData<NoteSummaryModel>>

}