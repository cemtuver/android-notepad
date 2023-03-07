package tuver.notepad.data.local.mapper.impl

import tuver.notepad.data.local.dto.NoteDto
import tuver.notepad.data.local.mapper.NoteSummaryMapper
import tuver.notepad.model.NoteSummaryModel
import java.util.*
import javax.inject.Inject

class NoteSummaryMapperImpl @Inject constructor() : NoteSummaryMapper {

    override fun toModel(noteDto: NoteDto, shortDescriptionCharLimit: Int): NoteSummaryModel {
        return noteDto.run {
            NoteSummaryModel(
                id = id.toString(),
                title = title,
                imageUrl = imageUrl,
                shortDescription = description.take(shortDescriptionCharLimit),
                createdDate = Date(createdDate),
                isUpdated = updatedDate != null
            )
        }
    }

}