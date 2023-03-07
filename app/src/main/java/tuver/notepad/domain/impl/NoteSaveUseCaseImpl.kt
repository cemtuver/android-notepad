package tuver.notepad.domain.impl

import tuver.notepad.data.NoteRepository
import tuver.notepad.domain.NoteSaveUseCase
import tuver.notepad.model.NoteModel
import javax.inject.Inject

class NoteSaveUseCaseImpl @Inject constructor(
    private val noteRepository: NoteRepository
) : NoteSaveUseCase {

    override suspend fun saveNote(noteModel: NoteModel): Result<NoteModel> {
        return when {
            noteModel.isNew -> noteRepository.createNote(noteModel)
            else -> noteRepository.updateNote(noteModel)
        }
    }

}