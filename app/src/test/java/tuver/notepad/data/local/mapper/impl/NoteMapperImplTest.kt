package tuver.notepad.data.local.mapper.impl

import tuver.notepad.data.local.dto.NoteDto
import tuver.notepad.model.NoteModel
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

internal class NoteMapperImplTest {

    private val noteMapperImpl = NoteMapperImpl()

    @Test
    fun `should map dto to model`() {
        // given
        val id = UUID.randomUUID()
        val createdDate = Date()
        val updatedDate = Date()
        val noteDto = NoteDto(id, "title", "https://image.url/", "description", createdDate.time, updatedDate.time)
        val expectedNoteModel = NoteModel(id.toString(), "title", "https://image.url/", "description", createdDate, updatedDate)

        // when
        val noteModel = noteMapperImpl.toModel(noteDto)

        // then
        assertEquals(expectedNoteModel, noteModel)
    }

    @Test
    fun `should map model to dto`() {
        // given
        val id = UUID.randomUUID()
        val createdDate = Date()
        val updatedDate = Date()
        val noteModel = NoteModel(id.toString(), "title", "https://image.url/", "description", createdDate, updatedDate)
        val expectedNoteDto = NoteDto(id, "title", "https://image.url/", "description", createdDate.time, updatedDate.time)

        // when
        val noteDto = noteMapperImpl.toDto(noteModel)

        // then
        assertEquals(expectedNoteDto, noteDto)
    }

}