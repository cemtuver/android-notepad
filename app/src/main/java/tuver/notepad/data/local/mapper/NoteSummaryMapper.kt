package tuver.notepad.data.local.mapper

import tuver.notepad.data.local.dto.NoteDto
import tuver.notepad.model.NoteSummaryModel

interface NoteSummaryMapper {

    fun toModel(noteDto: NoteDto, shortDescriptionCharLimit: Int): NoteSummaryModel

}