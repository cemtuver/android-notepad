package tuver.notepad.data.local.mapper

import tuver.notepad.data.local.dto.NoteDto
import tuver.notepad.model.NoteModel

interface NoteMapper {

    fun toModel(dto: NoteDto): NoteModel

    fun toDto(model: NoteModel): NoteDto

}