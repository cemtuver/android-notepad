package tuver.notepad.domain

import tuver.notepad.model.NoteModel

interface NoteGetUseCase {

    suspend fun getNote(noteId: String): Result<NoteModel>

}