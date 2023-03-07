package tuver.notepad.ui.notesummarylist

import androidx.paging.PagingData
import com.jraska.livedata.test
import tuver.notepad.domain.NoteGetNoteSummaryListPagingDataUseCase
import tuver.notepad.model.NoteSummaryModel
import tuver.notepad.ui.BaseViewModelTest
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotNull
import org.junit.Test
import kotlin.random.Random

@OptIn(DelicateCoroutinesApi::class, ExperimentalCoroutinesApi::class)
internal class NoteSummaryListViewModelTest : BaseViewModelTest() {

    private val pageSize = Random.nextInt()

    private val shortDescriptionCharLimit = Random.nextInt()

    private val mockNoteGetNoteSummaryListPagingDataUseCase: NoteGetNoteSummaryListPagingDataUseCase = mockk()

    private val noteSummaryListViewModel = NoteSummaryListViewModel(
        pageSize,
        shortDescriptionCharLimit,
        mockNoteGetNoteSummaryListPagingDataUseCase
    )

    @Test
    fun `should get paging flow from use case and emit it via state`() = runTest {
        // given
        val expectedPagingData: PagingData<NoteSummaryModel> = PagingData.from(listOf())
        every {
            mockNoteGetNoteSummaryListPagingDataUseCase.getNoteSummaryListPagingData(eq(pageSize), eq(shortDescriptionCharLimit))
        } returns flowOf(expectedPagingData)

        // when
        val pagingData = noteSummaryListViewModel.state.noteListPagingData.first()

        // then
        verify {
            mockNoteGetNoteSummaryListPagingDataUseCase.getNoteSummaryListPagingData(eq(pageSize), eq(shortDescriptionCharLimit))
        }
        confirmVerified(mockNoteGetNoteSummaryListPagingDataUseCase)
        assertNotNull(pagingData)
    }

    @Test
    fun `should emit navigate to note create when create note button click`() = runTest {
        // given
        val expectedNavigation = NoteSummaryListNavigation.NavigateToNoteCreate

        // when
        noteSummaryListViewModel.onCreateNoteButtonClick()

        // then
        noteSummaryListViewModel.navigation
            .test()
            .awaitValue()
            .assertValue(expectedNavigation)
    }

    @Test
    fun `should emit navigate to note edit when note click`() = runTest {
        // given
        val noteSummaryModel: NoteSummaryModel = mockk()
        val expectedNavigation = NoteSummaryListNavigation.NavigateToNoteEdit(noteSummaryModel)

        // when
        noteSummaryListViewModel.onNoteSummaryClick(noteSummaryModel)

        // then
        noteSummaryListViewModel.navigation
            .test()
            .awaitValue()
            .assertValue(expectedNavigation)
    }

}