package tuver.notepad.ui.noteedit

import com.jraska.livedata.test
import tuver.notepad.domain.NoteDeleteUseCase
import tuver.notepad.domain.NoteGetUseCase
import tuver.notepad.domain.NoteSaveUseCase
import tuver.notepad.model.NoteModel
import tuver.notepad.model.NoteSummaryModel
import tuver.notepad.ui.BaseViewModelTest
import io.mockk.*
import kotlinx.coroutines.delay
import org.junit.Test
import java.util.*

class NoteEditViewModelTest : BaseViewModelTest() {

    private val noteSummaryModel = NoteSummaryModel(
        id = UUID.randomUUID().toString(),
        title = "title",
        imageUrl = "https://image.url/",
        shortDescription = "short description",
        createdDate = Date(),
        isUpdated = false
    )

    private val noteModel = NoteModel(
        id = noteSummaryModel.id,
        title = noteSummaryModel.title,
        imageUrl = noteSummaryModel.imageUrl,
        description = "full description",
        createdDate = noteSummaryModel.createdDate,
        updatedDate = null
    )

    private val mockNoteGetUseCase: NoteGetUseCase = mockk()

    private val mockNoteSaveUseCase: NoteSaveUseCase = mockk()

    private val mockNoteDeleteUseCase: NoteDeleteUseCase = mockk()

    private lateinit var noteEditViewModel: NoteEditViewModel

    override fun onSetup() {
        coEvery { mockNoteGetUseCase.getNote(eq(noteSummaryModel.id)) } returns Result.success(noteModel)
        noteEditViewModel = NoteEditViewModel(
            noteSummaryModel,
            mockNoteGetUseCase,
            mockNoteSaveUseCase,
            mockNoteDeleteUseCase
        )
    }

    @Test
    fun `should emit note title, image url and description of note summary model and load note model via state`() {
        // given
        clearAllMocks()
        coEvery { mockNoteGetUseCase.getNote(eq(noteSummaryModel.id)) } coAnswers {
            delay(100L)
            Result.success(noteModel)
        }

        // when
        noteEditViewModel = NoteEditViewModel(
            noteSummaryModel,
            mockNoteGetUseCase,
            mockNoteSaveUseCase,
            mockNoteDeleteUseCase
        )

        // then
        coVerify { mockNoteGetUseCase.getNote(eq(noteModel.id)) }
        confirmVerified(mockNoteGetUseCase)
        noteEditViewModel.state.title
            .test()
            .assertValue(noteSummaryModel.title)

        noteEditViewModel.state.imageUrl
            .test()
            .assertValue(noteSummaryModel.imageUrl)

        noteEditViewModel.state.description
            .test()
            .awaitNextValue()
            .assertValueHistory(noteSummaryModel.shortDescription, noteModel.description)
    }

    @Test
    fun `should save note via use case and emit navigate up event when note is updated and back press`() {
        // given
        val savedNoteModel = noteModel.copy(title = "updated title", imageUrl = "https://updatedimage.url/", description = "updated description")
        coEvery { mockNoteSaveUseCase.saveNote(eq(savedNoteModel)) } returns Result.success(savedNoteModel)

        // when
        noteEditViewModel.state.title.value = savedNoteModel.title
        noteEditViewModel.state.imageUrl.value = savedNoteModel.imageUrl
        noteEditViewModel.state.description.value = savedNoteModel.description
        noteEditViewModel.onBackPress()

        // then
        coVerify { mockNoteSaveUseCase.saveNote(eq(savedNoteModel)) }
        confirmVerified(mockNoteSaveUseCase)

        noteEditViewModel.navigation
            .test()
            .awaitValue()
            .assertValue(NoteEditNavigation.NavigateUp)
    }

    @Test
    fun `should not save note via use case but emit navigate up event when note is not updated but back press`() {
        // given

        // when
        noteEditViewModel.onBackPress()

        // then
        confirmVerified(mockNoteSaveUseCase)

        noteEditViewModel.navigation
            .test()
            .awaitValue()
            .assertValue(NoteEditNavigation.NavigateUp)
    }

    @Test
    fun `should delete note via use case and emit navigate up event when delete button click while editing an existing note`() {
        // given
        coEvery { mockNoteDeleteUseCase.deleteNote(eq(noteModel)) } returns Result.success(Unit)

        // when
        noteEditViewModel.onDeleteNotClick()

        // then
        coVerify { mockNoteDeleteUseCase.deleteNote(eq(noteModel)) }
        confirmVerified(mockNoteSaveUseCase)

        noteEditViewModel.navigation
            .test()
            .awaitValue()
            .assertValue(NoteEditNavigation.NavigateUp)
    }

    @Test
    fun `should not delete note via use case but emit navigate up event when delete button click while creating a new note`() {
        // given
        val noteEditViewModel = NoteEditViewModel(null, mockNoteGetUseCase, mockNoteSaveUseCase, mockNoteDeleteUseCase)

        // when
        noteEditViewModel.onDeleteNotClick()

        // then
        confirmVerified(mockNoteSaveUseCase)

        noteEditViewModel.navigation
            .test()
            .awaitValue()
            .assertValue(NoteEditNavigation.NavigateUp)
    }

}