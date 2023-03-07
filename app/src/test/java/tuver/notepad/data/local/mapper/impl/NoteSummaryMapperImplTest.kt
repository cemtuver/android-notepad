package tuver.notepad.data.local.mapper.impl

import tuver.notepad.data.local.dto.NoteDto
import tuver.notepad.model.NoteSummaryModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.*

class NoteSummaryMapperImplTest {

    private val noteSummaryMapperImpl = NoteSummaryMapperImpl()

    @Test
    fun `should trim description and set is edited false while converting a long not edited note`() {
        // given
        val shortDescriptionCharLimit = 5
        val id = UUID.randomUUID()
        val timestamp = Date().time
        val noteDto = NoteDto(
            id,
            "title",
            "https://image.url/",
            "1234567890",
            timestamp,
            null
        )
        val expectedNoteSummaryModel = NoteSummaryModel(
            id.toString(),
            "title",
            "https://image.url/",
            "12345",
            Date(timestamp),
            false
        )

        // when
        val noteSummaryModel = noteSummaryMapperImpl.toModel(noteDto, shortDescriptionCharLimit)

        // then
        assertEquals(expectedNoteSummaryModel, noteSummaryModel)
    }

    @Test
    fun `should set is edited true while converting an edited note`() {
        // given
        val shortDescriptionCharLimit = 5
        val id = UUID.randomUUID()
        val timestamp = Date().time
        val noteDto = NoteDto(
            id,
            "title",
            "https://image.url/",
            "1234567890",
            timestamp,
            timestamp
        )

        // when
        val noteSummaryModel = noteSummaryMapperImpl.toModel(noteDto, shortDescriptionCharLimit)

        // then
        assertTrue(noteSummaryModel.isUpdated)
    }

}