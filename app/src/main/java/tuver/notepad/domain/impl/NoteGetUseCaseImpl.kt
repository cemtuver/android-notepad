package tuver.notepad.domain.impl

import tuver.notepad.data.NoteRepository
import tuver.notepad.domain.NoteGetUseCase
import tuver.notepad.model.NoteModel
import javax.inject.Inject

class NoteGetUseCaseImpl @Inject constructor(
    private val noteRepository: NoteRepository
) : NoteGetUseCase {

    override suspend fun getNote(noteId: String): Result<NoteModel> {
        return noteRepository.getNote(noteId)
    }

}