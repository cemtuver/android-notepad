package tuver.notepad.data.local.mapper.impl

import tuver.notepad.data.local.dto.NoteDto
import tuver.notepad.data.local.mapper.NoteMapper
import tuver.notepad.model.NoteModel
import java.util.*
import javax.inject.Inject

class NoteMapperImpl @Inject constructor() : NoteMapper {

    override fun toModel(dto: NoteDto): NoteModel {
        return dto.run {
            NoteModel(
                id.toString(),
                title,
                imageUrl,
                description,
                Date(createdDate),
                updatedDate?.let { Date(it) }
            )
        }
    }

    override fun toDto(model: NoteModel): NoteDto {
        return model.run {
            NoteDto(
                UUID.fromString(id),
                title,
                imageUrl,
                description,
                createdDate.time,
                updatedDate?.time
            )
        }
    }

}