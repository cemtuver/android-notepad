package tuver.notepad.domain.impl

import tuver.notepad.data.NoteRepository
import tuver.notepad.domain.NoteDeleteUseCase
import tuver.notepad.model.NoteModel
import javax.inject.Inject

class NoteDeleteUseCaseImpl @Inject constructor(
    private val noteRepository: NoteRepository
) : NoteDeleteUseCase {

    override suspend fun deleteNote(noteModel: NoteModel): Result<Unit> {
        return noteRepository.deleteNote(noteModel)
    }

}