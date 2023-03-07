package tuver.notepad.domain

import tuver.notepad.model.NoteModel

interface NoteDeleteUseCase {

    suspend fun deleteNote(noteModel: NoteModel): Result<Unit>

}