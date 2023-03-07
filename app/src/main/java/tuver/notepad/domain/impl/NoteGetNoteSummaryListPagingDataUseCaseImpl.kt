package tuver.notepad.domain.impl

import androidx.paging.PagingData
import tuver.notepad.data.NoteSummaryRepository
import tuver.notepad.domain.NoteGetNoteSummaryListPagingDataUseCase
import tuver.notepad.model.NoteSummaryModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteGetNoteSummaryListPagingDataUseCaseImpl @Inject constructor(
    private val noteSummaryRepository: NoteSummaryRepository
) : NoteGetNoteSummaryListPagingDataUseCase {

    override fun getNoteSummaryListPagingData(
        pageSize: Int,
        shortDescriptionCharLimit: Int
    ): Flow<PagingData<NoteSummaryModel>> {
        return noteSummaryRepository.getNoteSummaryListPagingData(pageSize, shortDescriptionCharLimit)
    }

}