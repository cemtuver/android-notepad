package tuver.notepad.data.local.mapper.impl

import tuver.notepad.data.local.dto.NoteUpdateDto
import tuver.notepad.data.local.mapper.NoteUpdateMapper
import tuver.notepad.model.NoteModel
import java.util.*
import javax.inject.Inject

class NoteUpdateMapperImpl @Inject constructor() : NoteUpdateMapper {

    override fun toDto(model: NoteModel): NoteUpdateDto {
        return model.run {
            NoteUpdateDto(
                UUID.fromString(id),
                title,
                imageUrl,
                description,
                Date().time
            )
        }
    }

}