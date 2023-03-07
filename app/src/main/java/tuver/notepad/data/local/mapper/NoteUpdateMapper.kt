package tuver.notepad.data.local.mapper

import tuver.notepad.data.local.dto.NoteUpdateDto
import tuver.notepad.model.NoteModel

interface NoteUpdateMapper {

    fun toDto(model: NoteModel): NoteUpdateDto

}