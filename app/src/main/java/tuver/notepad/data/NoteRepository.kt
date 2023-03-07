package tuver.notepad.data

import tuver.notepad.model.NoteModel

interface NoteRepository {

    suspend fun getNote(noteId: String): Result<NoteModel>

    suspend fun createNote(noteModel: NoteModel): Result<NoteModel>

    suspend fun updateNote(noteModel: NoteModel): Result<NoteModel>

    suspend fun deleteNote(noteModel: NoteModel): Result<Unit>

}