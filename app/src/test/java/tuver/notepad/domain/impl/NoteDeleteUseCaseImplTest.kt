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

class NoteDeleteUseCaseImplTest {

    private val mockNoteRepository: NoteRepository = mockk()

    private val noteDeleteUseCaseImpl = NoteDeleteUseCaseImpl(mockNoteRepository)

    @Test
    fun `should delete note via repository while deleting note`() {
        // given
        val noteModel: NoteModel = mockk()
        val expectedResult = Result.success(Unit)
        coEvery { mockNoteRepository.deleteNote(eq(noteModel)) } returns expectedResult

        // when
        val result = runBlocking { noteDeleteUseCaseImpl.deleteNote(noteModel) }

        // then
        assertEquals(expectedResult, result)
        coVerify { mockNoteRepository.deleteNote(eq(noteModel)) }
        confirmVerified(mockNoteRepository)
    }

}