package tuver.notepad.data.local

import tuver.notepad.data.local.database.NoteDao
import tuver.notepad.data.local.dto.NoteDto
import tuver.notepad.data.local.dto.NoteUpdateDto
import tuver.notepad.data.local.mapper.NoteMapper
import tuver.notepad.data.local.mapper.NoteUpdateMapper
import tuver.notepad.model.NoteModel
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import java.util.*

internal class LocalNoteRepositoryTest {

    private val mockNoteDao: NoteDao = mockk()

    private val mockNoteMapper: NoteMapper = mockk()

    private val mockNoteUpdateMapper: NoteUpdateMapper = mockk()

    private val localNoteRepository = LocalNoteRepository(mockNoteDao, mockNoteMapper, mockNoteUpdateMapper)

    @Test
    fun `should select dto via dao while getting note`() {
        // given
        val noteId = UUID.randomUUID()
        val noteDto: NoteDto = mockk()
        val noteModel: NoteModel = mockk()
        val expectedResult = Result.success(noteModel)
        coEvery { mockNoteDao.select(eq(noteId)) } returns noteDto
        every { mockNoteMapper.toModel(eq(noteDto)) } returns noteModel

        // when
        val result = runBlocking { localNoteRepository.getNote(noteId.toString()) }

        // then
        coVerify { mockNoteDao.select(eq(noteId)) }
        confirmVerified(mockNoteDao)
        verify { mockNoteMapper.toModel(eq(noteDto)) }
        confirmVerified(mockNoteMapper)
        assertEquals(expectedResult, result)
    }

    @Test
    fun `should create dto and insert to dao while creating note`() {
        // given
        val noteModel = NoteModel(
            title = "title",
            imageUrl = "https://image.url/",
            description = "description"
        )

        val noteModelSlot = slot<NoteModel>()
        val noteDto: NoteDto = mockk()
        every { mockNoteMapper.toDto(capture(noteModelSlot)) } returns noteDto
        coEvery { mockNoteDao.insert(eq(noteDto)) } returns Unit

        // when
        val result = runBlocking { localNoteRepository.createNote(noteModel) }
        val resultNoteModel = result.getOrThrow()

        // then
        verify { mockNoteMapper.toDto(capture(noteModelSlot)) }
        confirmVerified(mockNoteMapper)
        coVerify { mockNoteDao.insert(eq(noteDto)) }
        confirmVerified(mockNoteDao)
        assertEquals(noteModelSlot.captured, resultNoteModel)
        assertTrue(resultNoteModel.id.isNotEmpty())
        assertNotNull(noteModel.title, resultNoteModel.title)
        assertNotNull(noteModel.imageUrl, resultNoteModel.imageUrl)
        assertNotNull(noteModel.description, resultNoteModel.description)
        assertNotNull(resultNoteModel.createdDate)
        assertNull(resultNoteModel.updatedDate)
    }

    @Test
    fun `should override id, created date and updated date while creating note`() {
        // given
        val timestamp = Date().time - 10000
        val noteModel = NoteModel(
            id = "id",
            title = "title",
            imageUrl = "https://image.url/",
            description = "description",
            createdDate = Date(timestamp),
            updatedDate = Date(timestamp)
        )

        val noteModelSlot = slot<NoteModel>()
        val noteDto: NoteDto = mockk()
        every { mockNoteMapper.toDto(capture(noteModelSlot)) } returns noteDto
        coEvery { mockNoteDao.insert(eq(noteDto)) } returns Unit

        // when
        runBlocking { localNoteRepository.createNote(noteModel) }

        // then
        verify { mockNoteMapper.toDto(capture(noteModelSlot)) }
        confirmVerified(mockNoteMapper)
        coVerify { mockNoteDao.insert(eq(noteDto)) }
        confirmVerified(mockNoteDao)
        assertNotEquals(noteModel.id, noteModelSlot.captured.id)
        assertNotEquals(noteModel.createdDate, noteModelSlot.captured.createdDate)
        assertNotNull(noteModelSlot.captured.createdDate)
        assertNull(noteModelSlot.captured.updatedDate)
    }

    @Test
    fun `should create update dto and override updated date while updating note`() {
        // given
        val id = UUID.randomUUID()
        val timestamp = Date().time - 10000
        val noteModel = NoteModel(
            id = id.toString(),
            title = "title",
            imageUrl = "https://image.url/",
            description = "description",
            createdDate = Date(timestamp),
            updatedDate = Date(timestamp)
        )

        val noteUpdateDto = NoteUpdateDto(
            id = id,
            title = "title",
            imageUrl = "https://image.url/",
            description = "description",
            updatedDate = Date().time
        )

        val expectedResult = Result.success(noteModel.copy(updatedDate = noteUpdateDto.updatedDate?.let { Date(it) }))

        every { mockNoteUpdateMapper.toDto(eq(noteModel)) } returns noteUpdateDto
        coEvery { mockNoteDao.update(eq(noteUpdateDto)) } returns Unit

        // when
        val result = runBlocking { localNoteRepository.updateNote(noteModel) }

        // then
        verify { mockNoteUpdateMapper.toDto(eq(noteModel)) }
        confirmVerified(mockNoteUpdateMapper)
        coVerify { mockNoteDao.update(eq(noteUpdateDto)) }
        confirmVerified(mockNoteDao)
        assertEquals(expectedResult, result)
    }

    @Test
    fun `should delete note via dao while deleting note`() {
        // given
        val noteModel: NoteModel = mockk()
        val noteDto: NoteDto = mockk()
        val expectedResult = Result.success(Unit)
        every { mockNoteMapper.toDto(eq(noteModel)) } returns noteDto
        coEvery { mockNoteDao.delete(eq(noteDto)) } returns Unit

        // when
        val result = runBlocking { localNoteRepository.deleteNote(noteModel) }

        // then
        verify { mockNoteMapper.toDto(eq(noteModel)) }
        confirmVerified(mockNoteMapper)
        coVerify { mockNoteDao.delete(eq(noteDto)) }
        confirmVerified(mockNoteDao)
        assertEquals(expectedResult, result)
    }

}