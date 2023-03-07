package tuver.notepad.data.local

import tuver.notepad.data.NoteRepository
import tuver.notepad.data.local.database.NoteDao
import tuver.notepad.data.local.mapper.NoteMapper
import tuver.notepad.data.local.mapper.NoteUpdateMapper
import tuver.notepad.model.NoteModel
import tuver.notepad.util.extension.tryResult
import java.util.*
import javax.inject.Inject

class LocalNoteRepository @Inject constructor(
    private val noteDao: NoteDao,
    private val noteMapper: NoteMapper,
    private val noteUpdateMapper: NoteUpdateMapper
) : NoteRepository {

    override suspend fun getNote(noteId: String): Result<NoteModel> {
        return tryResult {
            noteDao.select(UUID.fromString(noteId)).let(noteMapper::toModel)
        }
    }

    override suspend fun createNote(noteModel: NoteModel): Result<NoteModel> {
        val newNoteModel = noteModel.copy(
            id = UUID.randomUUID().toString(),
            createdDate = Date(),
            updatedDate = null
        )

        return tryResult {
            noteDao.insert(noteMapper.toDto(newNoteModel))
            newNoteModel
        }
    }

    override suspend fun updateNote(noteModel: NoteModel): Result<NoteModel> {
        val noteUpdateDto = noteUpdateMapper.toDto(noteModel)

        return tryResult {
            noteDao.update(noteUpdateDto)
            noteModel.copy(updatedDate = noteUpdateDto.updatedDate?.let { Date(it) })
        }
    }

    override suspend fun deleteNote(noteModel: NoteModel): Result<Unit> {
        val noteDto = noteMapper.toDto(noteModel)

        return tryResult {
            noteDao.delete(noteDto)
        }
    }

}