package tuver.notepad.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import tuver.notepad.data.local.dto.NoteDto

@Database(entities = [NoteDto::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {

        const val NAME = "notes-db"

    }

}