package tuver.notepad.data.local.mapper.impl

import tuver.notepad.model.NoteModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import java.util.*

class NoteUpdateMapperImplTest {

    private val noteUpdateMapperImpl = NoteUpdateMapperImpl()

    @Test
    fun `should override updated date while mapping model to dto`() {
        val id = UUID.randomUUID()
        val timestamp = Date().time - 10000
        val createdDate = Date(timestamp)
        val updatedDate = Date(timestamp)
        val noteModel = NoteModel(id.toString(), "title", "https://image.url/", "description", createdDate, updatedDate)

        // when
        val noteDto = noteUpdateMapperImpl.toDto(noteModel)

        // then
        assertEquals(noteModel.id, noteDto.id.toString())
        assertEquals(noteModel.title, noteDto.title)
        assertEquals(noteModel.imageUrl, noteDto.imageUrl)
        assertEquals(noteModel.description, noteDto.description)
        assertNotEquals(noteModel.updatedDate, noteDto.updatedDate)
    }

}