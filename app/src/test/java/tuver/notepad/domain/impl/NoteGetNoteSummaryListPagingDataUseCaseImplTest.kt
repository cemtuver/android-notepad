package tuver.notepad.domain.impl

import androidx.paging.PagingData
import tuver.notepad.data.NoteSummaryRepository
import tuver.notepad.model.NoteSummaryModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.random.Random

internal class NoteGetNoteSummaryListPagingDataUseCaseImplTest {

    private val mockNoteSummaryRepository: NoteSummaryRepository = mockk()

    private val noteGetNoteListPagingDataUseCaseImpl = NoteGetNoteSummaryListPagingDataUseCaseImpl(mockNoteSummaryRepository)

    @Test
    fun `should return paging data flow from the repository while getting note list paging data`() {
        // given
        val pageSize = Random.nextInt()
        val shortDescriptionCharLimit = Random.nextInt()
        val expectedNoteListPagingData: Flow<PagingData<NoteSummaryModel>> = mockk()
        coEvery {
            mockNoteSummaryRepository.getNoteSummaryListPagingData(eq(pageSize), eq(shortDescriptionCharLimit))
        } returns expectedNoteListPagingData

        // when
        val noteListPagingData = runBlocking {
            noteGetNoteListPagingDataUseCaseImpl.getNoteSummaryListPagingData(pageSize, shortDescriptionCharLimit)
        }

        // then
        coVerify {
            mockNoteSummaryRepository.getNoteSummaryListPagingData(eq(pageSize), eq(shortDescriptionCharLimit))
        }
        confirmVerified(mockNoteSummaryRepository)
        assertEquals(expectedNoteListPagingData, noteListPagingData)
    }

}