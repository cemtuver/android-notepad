package tuver.notepad.domain.impl

import tuver.notepad.data.NoteRepository
import tuver.notepad.model.NoteModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

internal class NoteSaveUseCaseImplTest {

    private val mockNoteRepository: NoteRepository = mockk()

    private val noteSaveUseCaseImpl = NoteSaveUseCaseImpl(mockNoteRepository)

    @Test
    fun `should create note via repository while saving a new note`() {
        // given
        val noteModel = NoteModel(title = "title", description = "description")
        val expectedResult = Result.success(noteModel)
        coEvery { mockNoteRepository.createNote(eq(noteModel)) } returns expectedResult

        // when
        val result = runBlocking { noteSaveUseCaseImpl.saveNote(noteModel) }

        // then
        coVerify { mockNoteRepository.createNote(eq(noteModel)) }
        confirmVerified(mockNoteRepository)
        assertEquals(expectedResult, result)
    }

    @Test
    fun `should update note via repository while saving an existing note`() {
        // given
        val noteModel = NoteModel(id = "id", title = "title", description = "description")
        val expectedResult = Result.success(noteModel)
        coEvery { mockNoteRepository.updateNote(eq(noteModel)) } returns expectedResult

        // when
        val result = runBlocking { noteSaveUseCaseImpl.saveNote(noteModel) }

        // then
        coVerify { mockNoteRepository.updateNote(eq(noteModel)) }
        confirmVerified(mockNoteRepository)
        assertEquals(expectedResult, result)
    }

}