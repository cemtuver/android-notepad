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
import java.util.*

class NoteGetUseCaseImplTest {

    private val mockNoteRepository: NoteRepository = mockk()

    private val noteGetUseCaseImpl = NoteGetUseCaseImpl(mockNoteRepository)

    @Test
    fun `should get note via repository while getting`() {
        // given
        val id = UUID.randomUUID().toString()
        val noteModel: NoteModel = mockk()
        val expectedResult = Result.success(noteModel)
        coEvery { mockNoteRepository.getNote(eq(id)) } returns expectedResult

        // when
        val result = runBlocking { noteGetUseCaseImpl.getNote(id) }

        // then
        coVerify { mockNoteRepository.getNote(eq(id)) }
        confirmVerified(mockNoteRepository)
        assertEquals(expectedResult, result)
    }

}