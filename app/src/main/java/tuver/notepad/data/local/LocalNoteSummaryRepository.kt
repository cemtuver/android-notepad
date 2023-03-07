package tuver.notepad.data.local

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import tuver.notepad.data.NoteSummaryRepository
import tuver.notepad.data.local.database.NoteDao
import tuver.notepad.data.local.mapper.NoteSummaryMapper
import tuver.notepad.model.NoteSummaryModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalNoteSummaryRepository @Inject constructor(
    private val noteDao: NoteDao,
    private val noteSummaryMapper: NoteSummaryMapper
) : NoteSummaryRepository {

    override fun getNoteSummaryListPagingData(
        pageSize: Int,
        shortDescriptionCharLimit: Int
    ): Flow<PagingData<NoteSummaryModel>> {
        return Pager(PagingConfig(pageSize), null) { noteDao.selectPagingSource() }
            .flow
            .map { pagingData ->
                pagingData.map {
                    noteSummaryMapper.toModel(it, shortDescriptionCharLimit)
                }
            }
    }

}