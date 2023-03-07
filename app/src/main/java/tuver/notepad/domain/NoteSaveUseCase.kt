package tuver.notepad.domain

import tuver.notepad.model.NoteModel

interface NoteSaveUseCase {

    suspend fun saveNote(noteModel: NoteModel): Result<NoteModel>

}